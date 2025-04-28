package com.example.properties;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Value;

@Prototype
public class ValueBean {
    String uninterpolatedParameter;
    String interpolatedPropertyParameter;

    @Value("test.expression")
    String uninterpolated;

    @Value("${test.expression}")
    String interpolatedProperty;

    public ValueBean(@Value("test.expression") String uninterpolated,
                     @Value("${test.expression}") String interpolatedProperty) {
        this.uninterpolatedParameter = uninterpolated;
        this.interpolatedPropertyParameter = interpolatedProperty;
    }
}
