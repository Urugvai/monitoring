package org.morozov.monitoring.services;

import org.jetbrains.annotations.Nullable;
import org.morozov.monitoring.dao.MonitoringDao;
import org.morozov.monitoring.entities.User;
import org.morozov.monitoring.entities.ValuesHistoryItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Service(MonitoringService.NAME)
public class MonitoringServiceImpl implements MonitoringService {

    @Inject
    private MonitoringDao monitoringDao;

    @Override
    public List<ValuesHistoryItem> getValuesHistoryItemsByUserLogin(String userLogin) {
        return monitoringDao.loadValuesHistoryItemsByUserLogin(userLogin);
    }

    @Nullable
    @Override
    public User loadUser(String login, @Nullable String ping) {
        return monitoringDao.loadUser(login, ping);
    }

    @Transactional
    @Override
    public void writeValues(User user, double gas, double cold, double hotWater) {
        ValuesHistoryItem item = new ValuesHistoryItem();
        item.setUser(user);
        item.setGasValue(gas);
        item.setColdValue(cold);
        item.setHotWaterValue(hotWater);
        item.setEventDate(new Date());
        monitoringDao.save(item);
    }
}
