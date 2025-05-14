package com.example.taskforlat2025.Entities;

import com.example.taskforlat2025.Entities.shared.ValueCurrency;
import com.example.taskforlat2025.util.CurrencyConverter;
import com.example.taskforlat2025.util.CurrencyConverterExternal;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class CollectionBox {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long BoxId;

    @ManyToOne
    @JoinColumn(name="EventId")
    @Nullable
    public Event event;

    @ElementCollection
    private List<ValueCurrency> deposited;

    public CollectionBox() {
        deposited = new ArrayList<>();
    }

    public void setBoxId(long boxId) {
        BoxId = boxId;
    }

    public void setEvent(@Nullable Event event) {
        this.event = event;
    }

    public void setDeposited(List<ValueCurrency> deposited) {
        this.deposited = deposited;
    }

    public long getBoxId() {
        return BoxId;
    }

    @Nullable
    public Event getEvent() {
        return event;
    }

    public List<ValueCurrency> getDeposited() {
        return deposited;
    }

    public CollectionBox put(double value, String currency){
        for (ValueCurrency v : deposited) {
            if (v.getCurrency().equals(currency)) {
                v.add(value);
                return this;
            }
        }
        deposited.add(new ValueCurrency(value, currency));
        return this;
    }

    public boolean isEmpty(){
        if (deposited.isEmpty()){
            return true;
        }else{
            for (ValueCurrency v : deposited){
                if (v.getAmount().toBigInteger().doubleValue() > 0.0){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isAssigned(){
        return event != null;
    }

    public Event transferAll(CurrencyConverter converter){
        if(event == null){
            throw new RuntimeException("No Event Assigned");
        }
        String event_currency = event.getCurrency();
        for (ValueCurrency v : deposited){
            double converted = converter.convert(v.getAmount().toBigInteger().doubleValue(), v.getCurrency(), event_currency);
            event.addAmount(converted);
        }
        clearValue();
        return event;
    }

    public void clearValue(){
        deposited.clear();
    }
}
