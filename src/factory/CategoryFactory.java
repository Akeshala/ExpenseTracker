package factory;

import models.Category;

public class CategoryFactory {

    public static Category getCategory(String name){
        return new Category(name);
    }
}
