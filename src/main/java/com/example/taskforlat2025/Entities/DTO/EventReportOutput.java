package com.example.taskforlat2025.Entities.DTO;

import com.example.taskforlat2025.Entities.Event;

public class EventReportOutput {
    String name;
    String currency;
    double amount;

    public EventReportOutput() {
    }

    public EventReportOutput(String name, String currency, double amount) {
        this.name = name;
        this.currency = currency;
        this.amount = amount;
    }

    public EventReportOutput(Event event) {
        this.name = event.getName();
        this.currency = event.getCurrency();
        this.amount = event.getAmount().toBigInteger().doubleValue();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }
}
