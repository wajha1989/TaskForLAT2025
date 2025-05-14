package com.example.taskforlat2025.Entities;

import com.example.taskforlat2025.Entities.shared.ValueCurrency;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long EventId;

    private String name;

    private String currency;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    public Event() {}

    public Event(String currency, String name) {
        this.currency = currency;
        this.name = name;
        this.amount = new BigDecimal(0);
    }

    public void setEventId(long eventId) {
        EventId = eventId;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public long getEventId() {
        return EventId;
    }

    public String getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addAmount(double amount) {
        this.amount = this.amount.add(new BigDecimal(amount));
    }
}
