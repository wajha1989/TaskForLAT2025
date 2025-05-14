package com.example.taskforlat2025.Entities.DTO;

public class AddMoneyRequest {
    private String currency;
    private double amount;

    public AddMoneyRequest() {
    }

    public AddMoneyRequest(String currency) {
        this.currency = currency;
    }


    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
