package org.morozov.monitoring.controllers.helpers;

import org.morozov.monitoring.controllers.dto.ValuesHistoryItemDto;
import org.morozov.monitoring.controllers.responses.BaseResponse;
import org.morozov.monitoring.controllers.responses.GetValuesResponse;
import org.morozov.monitoring.entities.ValuesHistoryItem;

import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;

public class ResponseBuilder {

    public static BaseResponse buildGoodResponse(int code) {
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        return response;
    }

    public static BaseResponse buildErrorResponse(int errorCode, String errorMessage) {
        BaseResponse response = new BaseResponse();
        response.setCode(errorCode);
        response.setErrorMessage(errorMessage);
        return response;
    }

    public static BaseResponse buildGetValuesResponse(List<ValuesHistoryItem> valuesHistoryItems) {
        GetValuesResponse response = new GetValuesResponse();
        response.setCode(HttpURLConnection.HTTP_OK);
        List<ValuesHistoryItemDto> dtoList =
                valuesHistoryItems.stream().map(ResponseBuilder::buildValuesHistoryItemDto).collect(Collectors.toList());
        response.setHistories(dtoList);
        return response;
    }

    private static ValuesHistoryItemDto buildValuesHistoryItemDto(ValuesHistoryItem valuesHistoryItem) {
        ValuesHistoryItemDto dto = new ValuesHistoryItemDto();
        dto.eventDate = valuesHistoryItem.getEventDate();
        dto.gasValue = valuesHistoryItem.getGasValue();
        dto.coldValue = valuesHistoryItem.getColdValue();
        dto.hotWaterValue = valuesHistoryItem.getHotWaterValue();
        return dto;
    }
}
