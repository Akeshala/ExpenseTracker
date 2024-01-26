package org.asd.models;

public class Income extends Transaction{

    public Income(double amount, int categoryID, String note, Boolean isRecurring) {
        setCategoryID(categoryID);
        setNote(note);
        setIsRecurring(isRecurring);
        setDateTime(System.currentTimeMillis());
        setAmount(amount);
    }
}
