package com.example.contextReplacement;

import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Replaces;

@Factory
@Replaces(MultiBeanFactory.class)
public class ReplacementBeanFactory {

//    @Context
    @Replaces(BeanApi1.class)
    @Primary
    @EachBean(DataBean.class)
    public BeanApi1 createReplacementBeanContext(DataBean dataBean) {
        return new ReplacementMultiBean(dataBean);
    }
}
