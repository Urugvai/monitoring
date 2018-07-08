package org.morozov.monitoring.services;

import org.jetbrains.annotations.Nullable;
import org.morozov.monitoring.entities.User;
import org.morozov.monitoring.entities.ValuesHistoryItem;

import java.util.List;

public interface MonitoringService {

    String NAME = "dataService";

    List<ValuesHistoryItem> getValuesHistoryItemsByUserLogin(String userLogin);

    @Nullable
    User loadUser(String login, @Nullable String ping);

    void writeValues(User user, double gas, double cold, double hotWater);
}
