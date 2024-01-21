package models;

public class Transaction {
    private String category;
    private double amount;
    private boolean isIncome;
    private boolean isRecurring;
    private String note;

    public Transaction(String category, double amount, boolean isIncome, boolean isRecurring, String note) {
        this.category = category;
        this.amount = amount;
        this.isIncome = isIncome;
        this.isRecurring = isRecurring;
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isExpense() {
        return !isIncome;
    }

    public boolean isIncome() {

        return isIncome;
    }

    public void setIsIncome(boolean isIncome) {

        this.isIncome = isIncome;
    }

    public boolean isRecurring() {

        return isRecurring;
    }

    public String getNote() {

        return note;
    }

    public void setCategory(String category) {

        this.category = category;
    }

    public void setAmount(double amount) {

        this.amount = amount;
    }

    public void setIsRecurring(boolean isRecurring) {

        this.isRecurring = isRecurring;
    }

    public void setNote(String note) {

        this.note = note;
    }
}