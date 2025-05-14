package com.example.taskforlat2025.util;

import java.io.IOException;

//An interface made for easy changing of converting method
public interface CurrencyConverter{
    public double convert(double amount, String from, String to);
}
