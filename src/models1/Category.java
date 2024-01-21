package models1;

public class Category {
    private Integer id;
    private String name;

    public Category(Integer id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return  name;
    }
    public Integer getId(){
        return  id;
    }
}