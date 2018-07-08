package org.morozov.monitoring.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "monitoring$ValuesHistoryItem")
@Table(name = "monitoring_values_history_item")
public class ValuesHistoryItem extends BaseEntity {

    @Column(name = "event_date", nullable = false)
    private Date eventDate;

    @Column(name = "gas_value")
    private Double gasValue;

    @Column(name = "cold_value")
    private Double coldValue;

    @Column(name = "hot_water_value")
    private Double hotWaterValue;

    @ManyToOne(targetEntity = User.class, optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public Double getGasValue() {
        return gasValue;
    }

    public void setGasValue(Double gasValue) {
        this.gasValue = gasValue;
    }

    public Double getColdValue() {
        return coldValue;
    }

    public void setColdValue(Double coldValue) {
        this.coldValue = coldValue;
    }

    public Double getHotWaterValue() {
        return hotWaterValue;
    }

    public void setHotWaterValue(Double hotWaterValue) {
        this.hotWaterValue = hotWaterValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
