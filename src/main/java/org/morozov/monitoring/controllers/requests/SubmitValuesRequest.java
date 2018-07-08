package org.morozov.monitoring.controllers.requests;

public class SubmitValuesRequest {

    private String login;
    private String pin;
    private Double gasValue;
    private Double coldValue;
    private Double hotWaterValue;

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
}
