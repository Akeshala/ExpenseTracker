package org.asd.factory;

import org.asd.models.Category;

public class CategoryFactory {

    public static Category getCategory(String name){
        return new Category(name);
    }
}
