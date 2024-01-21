package models1;

import java.time.LocalDateTime;
public class Income extends Transaction{
    public Income(Integer id, Money amount, Category category, String note, Boolean isRecurring, LocalDateTime dateTime) {
        this.id = id;
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.isRecurring = isRecurring;
        this.dateTime = dateTime;
    }
}
