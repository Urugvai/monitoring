package org.morozov.monitoring.controllers.responses;

import org.morozov.monitoring.controllers.dto.ValuesHistoryItemDto;

import java.util.List;

public class GetValuesResponse extends BaseResponse {

    private List<ValuesHistoryItemDto> histories;

    public List<ValuesHistoryItemDto> getHistories() {
        return histories;
    }

    public void setHistories(List<ValuesHistoryItemDto> histories) {
        this.histories = histories;
    }
}
