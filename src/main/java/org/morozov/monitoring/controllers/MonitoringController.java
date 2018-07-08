package org.morozov.monitoring.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.morozov.monitoring.controllers.helpers.ResponseBuilder;
import org.morozov.monitoring.controllers.requests.SubmitValuesRequest;
import org.morozov.monitoring.controllers.responses.BaseResponse;
import org.morozov.monitoring.entities.User;
import org.morozov.monitoring.entities.ValuesHistoryItem;
import org.morozov.monitoring.services.MonitoringService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.HttpURLConnection;
import java.util.List;

@RestController
public class MonitoringController {

    private static final Logger logger = LogManager.getLogger(MonitoringController.class);

    @Inject
    private MonitoringService monitoringService;

    @RequestMapping("/")
    @ResponseBody
    public String ping() {
        return "OK";
    }

    /**
     * We don't use any security options here, just return values by login.
     */
    @RequestMapping(value = "/getValues/{login}",
            method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BaseResponse submitValues(@PathVariable("login") String login) {
        //wa may load also all objects graph with this user
        User user = monitoringService.loadUser(login, null);
        if (user == null) {
            logger.warn(String.format("User by requested login '%s' is invalid", login));
            return ResponseBuilder.buildErrorResponse(
                    HttpURLConnection.HTTP_BAD_REQUEST, "User by requested login is invalid"
            );
        }

        List<ValuesHistoryItem> valuesHistoryItems = monitoringService.getValuesHistoryItemsByUserLogin(login);
        return ResponseBuilder.buildGetValuesResponse(valuesHistoryItems);
    }

    /**
     * For submitting values use pin for identification user.
     * Validate submitted values and add to old ones.
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public BaseResponse submitValues(@RequestBody SubmitValuesRequest submitValuesRequest) {
        User user = null;
        boolean isValid = true;
        //TODO better to compare strings values on blank (e.g. apache tool StringUtils.isBlank(String))
        if (submitValuesRequest.getLogin() == null || submitValuesRequest.getPin() == null)
            isValid = false;
        if (isValid)
            user = monitoringService.loadUser(submitValuesRequest.getLogin(), submitValuesRequest.getPin());
        if (!isValid || user == null) {
            logger.warn(
                    String.format(
                            "Invalid requested user '%s' or pin '%s'. Request is skipped.",
                            submitValuesRequest.getLogin(), submitValuesRequest.getPin()
                    )
            );
            return ResponseBuilder.buildErrorResponse(
                    HttpURLConnection.HTTP_BAD_REQUEST, "Invalid user or pin"
            );
        }

        if (isInvalidValues(submitValuesRequest)) {
            logger.warn(
                    String.format(
                            "Invalid one of values: gas '%s', cold '%s', hotWater '%s'",
                            submitValuesRequest.getGasValue(),
                            submitValuesRequest.getColdValue(),
                            submitValuesRequest.getHotWaterValue()
                    )
            );
            return ResponseBuilder.buildErrorResponse(
                    HttpURLConnection.HTTP_BAD_REQUEST, "Invalid measurements"
            );
        }

        monitoringService.writeValues(
                user, submitValuesRequest.getGasValue(), submitValuesRequest.getColdValue(), submitValuesRequest.getHotWaterValue()
        );

        return ResponseBuilder.buildGoodResponse(HttpURLConnection.HTTP_OK);
    }

    private boolean isInvalidValues(SubmitValuesRequest submitValuesRequest) {
        return (submitValuesRequest.getGasValue() != null && submitValuesRequest.getGasValue() < 0)
                || (submitValuesRequest.getColdValue() != null && submitValuesRequest.getColdValue() < 0)
                || (submitValuesRequest.getHotWaterValue() != null && submitValuesRequest.getHotWaterValue() < 0);
    }
}
