package com.example.replacement;

import io.micronaut.context.annotation.Context;

/**
 * Demonstrates replacement of beans. Initially, two prototype instances are injected. But if replacement is active,
 * the bean is replaced with other definition - even with another scope
 */
@Context
public class ConsumerBean {
    private final SimpleBean simpleBean;
    private final ProtoBean protoBean1;
    private final ProtoBean protoBean2;


    public ConsumerBean(SimpleBean simpleBean, ProtoBean protoBean1, ProtoBean protoBean2) {
        this.simpleBean = simpleBean;
        this.protoBean1 = protoBean1;
        this.protoBean2 = protoBean2;
        assert protoBean1 != protoBean2;
    }
}
