package com.example.contextReplacement;

import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.EachBean;
import io.micronaut.context.annotation.Factory;

@Factory
public class MultiBeanFactory {

    @Context
    @EachBean(DataBean.class)
    public BeanApi1 createBean(DataBean dataBean) {
        return new MultiBean(dataBean);
    }
}
