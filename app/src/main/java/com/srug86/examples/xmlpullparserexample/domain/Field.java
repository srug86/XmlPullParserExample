package com.srug86.examples.xmlpullparserexample.domain;

/**
 * Created by Sergio on 20/07/2015.
 */
public class Field {

    private String name;
    private FieldType type;
    private String value;

    public Field() { }

    public Field(String name, FieldType type, String value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
