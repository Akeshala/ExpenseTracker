package models;

public class Category {
    private Integer id;
    private String name;
    private Money amount;
    private Integer BudgetID;

    public Category(String name) {
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getId(){
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public double getAmount() {
        return amount.getValue();
    }
    public void setAmount(double amount) {
      this.amount = new Money(amount);
    }
    public Integer getBudgetID() {
        return BudgetID;
    }
    public void setBudgetID(Integer BudgetID) {
        this.BudgetID = BudgetID;
    }
}