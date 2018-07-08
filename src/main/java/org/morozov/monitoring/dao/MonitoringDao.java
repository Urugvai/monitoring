package org.morozov.monitoring.dao;

import org.jetbrains.annotations.Nullable;
import org.morozov.monitoring.entities.BaseEntity;
import org.morozov.monitoring.entities.User;
import org.morozov.monitoring.entities.ValuesHistoryItem;

import java.util.List;

public interface MonitoringDao {

    void save(BaseEntity entity);

    List<ValuesHistoryItem> loadValuesHistoryItemsByUserLogin(String login);

    @Nullable
    User loadUser(String login, @Nullable String pin);
}
