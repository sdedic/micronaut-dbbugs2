package com.example.eagerbeans;

import io.micronaut.context.annotation.Requires;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;

@Requires(property = "test.eagerBeans", value = "scheduled")
@Singleton
public class ScheduledBean extends TestedBean {
    public ScheduledBean(AppService appService) {
        super(appService);
    }

    @Scheduled(fixedDelay = "1s" /*, initialDelay = "5s" */)
    public void scheduledMethod(AppService service) {
        Thread.dumpStack();
        super.scheduledMethod(service);
    }
}
