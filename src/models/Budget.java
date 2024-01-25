package models;

public class Budget {
    private Integer id;
    private final Integer categoryID;
    private Money amount;

    public Budget(Integer categoryID, Double amount){
        this.categoryID = categoryID;
        setAmount(amount);
    }
    public Integer getId(){
        return id;
    }
    public Integer getCategoryID(){
        return categoryID;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public Money getAmount(){
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = new Money(amount);
    }
}