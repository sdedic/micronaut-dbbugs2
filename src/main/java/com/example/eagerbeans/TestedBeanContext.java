package com.example.eagerbeans;

import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Requires;

@Context
@Requires(property = "test.eagerBeans", value = "context")
public class TestedBeanContext extends TestedBean {
    public TestedBeanContext(AppService appService) {
        super(appService);
    }
}
