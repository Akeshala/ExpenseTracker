package models;

public class Income extends Transaction{

    public Income(double amount, int categoryID, String note, Boolean isRecurring) {
        setCategory(categoryID);
        setNote(note);
        setIsRecurring(isRecurring);
        setDateTime(System.currentTimeMillis());
        setAmount(amount);
    }
}
