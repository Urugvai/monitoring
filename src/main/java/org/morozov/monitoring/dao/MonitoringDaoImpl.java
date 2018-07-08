package org.morozov.monitoring.dao;

import org.jetbrains.annotations.Nullable;
import org.morozov.monitoring.entities.BaseEntity;
import org.morozov.monitoring.entities.User;
import org.morozov.monitoring.entities.ValuesHistoryItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class MonitoringDaoImpl implements MonitoringDao {

    @PersistenceContext
    private EntityManager em;


    @Override
    public void save(BaseEntity entity) {
        em.persist(entity);
    }

    @Override
    public List<ValuesHistoryItem> loadValuesHistoryItemsByUserLogin(String login) {
        TypedQuery<ValuesHistoryItem> query =
                em.createQuery(
                        "select vhi from monitoring$ValuesHistoryItem vhi " +
                                "where vhi.user.login = :login order by vhi.eventDate",
                        ValuesHistoryItem.class)
                        .setParameter("login", login);
        return query.getResultList();
    }

    @Nullable
    @Override
    public User loadUser(String login, @Nullable String pin) {
        StringBuilder queryBuilder = new StringBuilder("select u from monitoring$User u where u.login = :login");
        if (pin != null)
            queryBuilder.append(" and u.pin = :pin");

        TypedQuery<User> query = em.createQuery(queryBuilder.toString(), User.class)
                .setParameter("login", login);
        if (pin != null)
            query.setParameter("pin", pin);
        List<User> users = query.getResultList();
        return users.isEmpty() ? null : users.get(0);
    }
}
