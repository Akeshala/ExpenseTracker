package models;

public class Money {
    private final double value;

    public static final String ZERO = "Rs.0";

    public Money(double value) {
        this.value = value;
    }
    public double getValue(){return value;}

    public String getRupee() {
        return "Rs. " + value;
    }
}
