package com.srug86.examples.xmlpullparserexample.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergio on 20/07/2015.
 */
public class Template {

    private String name;
    private List<Category> categoryList = new ArrayList<>();

    public Template() { }

    public Template(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }
}
