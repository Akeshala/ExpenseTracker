package models1;

public class BudgetEntry {
    private Integer id;
    private Category category;
    private Money total;

    public BudgetEntry(Integer id, Category category, Money total){
        this.id = id;
        this.category= category;
        this.total = total;
    }
    public Category getCategoryType(){return category;}
    public float getTotal(){return total.getValue();}
    public Integer getId(){return id;}
}