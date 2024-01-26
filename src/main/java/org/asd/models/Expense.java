package org.asd.models;

public class Expense extends Transaction{
    public Expense(double amount, int categoryID, String note, Boolean isRecurring){
        setCategoryID(categoryID);
        setNote(note);
        setIsRecurring(isRecurring);
        setDateTime(System.currentTimeMillis());
        setAmount(amount);
    }
}
