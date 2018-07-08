package org.morozov.monitoring.entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "monitoring$User")
@Table(name = "monitoring_user")
public class User extends BaseEntity {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "pin", nullable = false)
    private String pin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private List<ValuesHistoryItem> valuesHistoryItems;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<ValuesHistoryItem> getValuesHistoryItems() {
        return valuesHistoryItems;
    }

    public void setValuesHistoryItems(List<ValuesHistoryItem> valuesHistoryItems) {
        this.valuesHistoryItems = valuesHistoryItems;
    }
}
