package models1;

import java.time.LocalDateTime;
public class Transaction {
    protected Integer id;
    protected Money amount;
    protected Category category;
    protected String note;
    protected Boolean isRecurring;
    protected LocalDateTime dateTime;

    public Money getAmount(){return amount;}
    public Category getCategory(){return category;}
    public String getNote(){return note;}
    public Boolean getRecurrence(){return isRecurring;}
    public LocalDateTime getDateTime(){return dateTime;}
    public Integer getId(){return id;}
    public void setAmount(Money amount){this.amount = amount;}
    public void setCategory(Category category){this.category = category;}
    public void setNote(String note){this.note = note;}
    public void setRecurrence(Boolean isRecurring){this.isRecurring = isRecurring;}
    public void setDateTime(LocalDateTime date){this.dateTime = date;}
}
