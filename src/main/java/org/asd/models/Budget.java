package org.asd.models;

public class Budget {
    private Integer id;
    private final Integer categoryID;
    private double amount;

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

    public double getAmount(){
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}