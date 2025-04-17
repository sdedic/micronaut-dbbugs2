package com.example.contextReplacement;

import io.micronaut.context.annotation.EachProperty;
import io.micronaut.context.annotation.Parameter;

@EachProperty(value = "replacement.multi", primary = "default")
public class DataBean {
    private String id;
    private String name;

    public DataBean(@Parameter String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
