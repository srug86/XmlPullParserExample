package com.srug86.examples.xmlpullparserexample.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 20/07/2015.
 */
public class Category {

    private String name;
    private CategoryType type;
    private List<Field> fieldList = new ArrayList<>();

    public Category() { }

    public Category(String name, CategoryType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryType getType() {
        return type;
    }

    public void setType(CategoryType type) {
        this.type = type;
    }

    public List<Field> getFieldList() {
        return fieldList;
    }
}
