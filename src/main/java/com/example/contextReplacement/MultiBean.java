package com.example.contextReplacement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultiBean implements BeanApi1 {
    private static final Logger LOG = LoggerFactory.getLogger(MultiBean.class);

    private final DataBean dataBean;

    public MultiBean(DataBean dataBean) {
        this.dataBean = dataBean;
        LOG.error(">>>>>>>>>>>> Initializing bean that has been replaced for (any) injection", new Throwable());
    }
}
