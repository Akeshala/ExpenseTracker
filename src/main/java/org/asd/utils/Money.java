package org.asd.utils;

public class Money {

    public static final String ZERO = "Rs.0";

    public static String getFormattedAmount(double value) {
        return "Rs." + value;
    }
}
