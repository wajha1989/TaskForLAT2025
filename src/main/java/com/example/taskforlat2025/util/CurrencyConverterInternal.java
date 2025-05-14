package com.example.taskforlat2025.util;

import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterInternal implements CurrencyConverter {
    private static final Map<String, Double> exchangeRates = new HashMap<>();
    //while technically possible to calculate reverse rate by simply reversing it (reverse=1/rate)
    //it is generally considered to be an approximation and not recommended
    static {
         exchangeRates.put("USD-EUR", 0.92);
         exchangeRates.put("EUR-USD", 1.09);
         exchangeRates.put("EUR-PLN", 4.25);
         exchangeRates.put("PLN-EUR", 0.24);
         exchangeRates.put("USD-PLN", 3.82);
         exchangeRates.put("PLN-USD", 0.26);
    }

    @Override
    public double convert(double amount, String from, String to) {
        if(from.equals(to)){
            return amount;
        }
        String exchange = from + "-" + to;
        if(!exchangeRates.containsKey(exchange)) {
            throw new IllegalArgumentException("Exchange rate not supported" + exchange);
        }
        return amount * exchangeRates.get(exchange);
    }
}
