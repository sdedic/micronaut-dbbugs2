package com.example.racebug;

import io.micronaut.context.annotation.Context;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Context
public class EagerService {
    private static final Logger LOG = LoggerFactory.getLogger(EagerService.class);
    private final RacebugRefreshSingleton refreshSingleton;

    public EagerService(RacebugRefreshSingleton refreshSingleton) {
        this.refreshSingleton = refreshSingleton;
        // get the name immediately to materialize the bean in RefreshScope
        refreshSingleton.getRefreshableName();
    }

    public String getInitialName() {
        return refreshSingleton.getInitialName();
    }

    public String getFreshName() {
        return refreshSingleton.getRefreshableName();
    }

    @Scheduled(fixedDelay = "30s")
    public void periodicRacebugPrint() {
        LOG.info("Periodic check: of racebug initial name: {}, current name {}, from refreshable #{}",
                refreshSingleton.getInitialName(), getFreshName(), refreshSingleton.getId());
    }
}
