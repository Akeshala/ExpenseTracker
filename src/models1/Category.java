package models1;

import java.sql.SQLOutput;

public class Category {
    private Integer id;
    private String name;

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

    public String getAmount() {
        return "Test Amount";
    }

    public void setAmount(int amount) {
//        System.out.println(amount);
    }
}