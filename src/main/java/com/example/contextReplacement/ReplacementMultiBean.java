package com.example.contextReplacement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplacementMultiBean implements BeanApi1 {
    private static final Logger LOG = LoggerFactory.getLogger(ReplacementMultiBean.class);

    private DataBean dataBean;

    public ReplacementMultiBean(DataBean dataBean) {
        this.dataBean = dataBean;
        LOG.info(">>>>>>>>>>>> Replacement bean screated for injection");
    }
}
