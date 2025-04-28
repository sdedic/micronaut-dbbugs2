package com.example.eagerbeans;


import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;

@Singleton
@Requires(missingProperty = "test.eagerBeans")
public class TestedBean {
    AppService appService;
    String cachedValue;

    public TestedBean(AppService appService) {
        this.appService = appService;
    }

    public void scheduledMethod(AppService service) {
        this.cachedValue = service.getValue();
    }

    public String getCachedValue() {
        return cachedValue;
    }
}
