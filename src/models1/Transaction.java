package models1;

import resources.Database1;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public abstract class Transaction {
    protected Integer id;
    protected Money amount;
    protected Integer categoryID;
    protected String note;
    protected Boolean isRecurring;
    protected Long timestamp;

    public Integer getId(){return id;}
    public void setId(Integer id) {this.id = id;}

    public Money getAmount(){return amount;}
    public String getDisplayAmount(){return amount.getRupee();}
    public void setAmount(Money amount){this.amount = amount;}
    public Category getCategory(){
        Database1 database1 = Database1.getInstance();
        return database1.getCategoryByID(categoryID);
    }
    public void setCategory(int categoryID){this.categoryID = categoryID;}

    public String getNote(){return note;}
    public void setNote(String note){this.note = note;}
    public Boolean getIsRecurring(){return isRecurring;}
    public void setIsRecurring(Boolean isRecurring){this.isRecurring = isRecurring;}
    public String getDateTime(){
        Instant instant = Instant.ofEpochMilli(timestamp);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
    public void setDateTime(Long timestamp){this.timestamp = timestamp;}

    public String toString() {
        return this.getCategory().getName() + ": " + this.getDisplayAmount() +
                " (Income: " + (this instanceof Income) + ", Recurring: " + this.getIsRecurring() +
                ", Time: " + this.getDateTime() + ")";
    }
}
