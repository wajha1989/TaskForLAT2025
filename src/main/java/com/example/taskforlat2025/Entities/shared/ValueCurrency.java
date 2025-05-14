package com.example.taskforlat2025.Entities.shared;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;


@Embeddable
public class ValueCurrency {

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;
    private String currency;

    public ValueCurrency() {
    }

    public ValueCurrency(double amount, String currency) {
        this.amount = new BigDecimal(amount);
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void add(double value){
        amount = this.amount.add(new BigDecimal(value));
    }
}
