package models1;

import java.time.LocalDateTime;
public class Income extends Transaction{

    public Income(Money amount, int categoryID, String note, Boolean isRecurring) {
        this.amount = amount;
        this.categoryID = categoryID;
        this.note = note;
        this.isRecurring = isRecurring;
        this.timestamp = System.currentTimeMillis();
    }
}
