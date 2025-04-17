package com.example.contextReplacement;

import io.micronaut.context.annotation.Context;
import io.micronaut.core.annotation.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Context
@Order(-1000)
public class ReplacementConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(ReplacementConsumer.class);

    public ReplacementConsumer(List<BeanApi1> allBeans) {
        LOG.info("Got {}, BeanAPIs: {}", allBeans.size(), allBeans);
        assert allBeans.get(0) instanceof ReplacementMultiBean;
    }
}
