package models1;

public class Expense extends Transaction{
    public Expense(Money amount, int categoryID, String note, Boolean isRecurring){
        this.amount = amount;
        this.categoryID = categoryID;
        this.note = note;
        this.isRecurring = isRecurring;
        this.timestamp = System.currentTimeMillis();
    }
}
