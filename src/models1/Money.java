package models1;

public class Money {
    private double value;

    public Money(double value) {
        this.value = value;
    }
    public double getValue(){return value;}

    public String getRupee() {
        return "Rs. " + value;
    }
}
