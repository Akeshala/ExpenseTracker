package models;

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

    public Integer getId(){
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Money getAmount(){
        return amount;
    }
    public void setAmount(double amount){
        this.amount = new Money(amount);
    }
    public Integer getCategoryID() {
        return categoryID;
    }
    public void setCategoryID(int categoryID){
        this.categoryID = categoryID;
    }
    public String getNote(){
        return note;
    }
    public void setNote(String note){
        this.note = note;
    }
    public Boolean getIsRecurring(){
        return isRecurring;
    }
    public void setIsRecurring(Boolean isRecurring){
        this.isRecurring = isRecurring;
    }
    public String getDateTime(){
        Instant instant = Instant.ofEpochMilli(timestamp);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
    public void setDateTime(Long timestamp){
        this.timestamp = timestamp;
    }
    public String getDetails() {
        return this.getAmount().getRupee() +
                " (Income: " + (this instanceof Income) + ", Recurring: " + this.getIsRecurring() +
                ", Time: " + this.getDateTime() + ")";
    }
}
