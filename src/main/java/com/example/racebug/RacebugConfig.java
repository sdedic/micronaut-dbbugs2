package com.example.racebug;

import io.micronaut.context.annotation.ConfigurationProperties;

@ConfigurationProperties("racebug-config")
public class RacebugConfig {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
