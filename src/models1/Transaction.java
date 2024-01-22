package models1;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Transaction {
    protected Integer id;
    protected Money amount;
    protected Category category;
    protected String note;
    protected Boolean isRecurring;
    protected Long timestamp;

    public Money getAmount(){return amount;}

    public String getDisplayAmount(){return amount.getRupee();}
    public Category getCategory(){return category;}
    public String getNote(){return note;}
    public Boolean getIsRecurring(){return isRecurring;}
    public String getDateTime(){
        Instant instant = Instant.ofEpochMilli(timestamp);
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }
    public Integer getId(){return id;}
    public void setAmount(Money amount){this.amount = amount;}
    public void setCategory(Category category){this.category = category;}
    public void setNote(String note){this.note = note;}
    public void setIsRecurring(Boolean isRecurring){this.isRecurring = isRecurring;}
    public void setDateTime(Long timestamp){this.timestamp = timestamp;}
}
