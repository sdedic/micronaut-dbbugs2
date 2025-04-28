package com.example.eagerbeans;

import jakarta.inject.Singleton;

@Singleton
public class AppService {
    private String value = "defaultValue";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
