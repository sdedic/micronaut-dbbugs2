package com.example.configrefresh;

import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventPublisher;
import io.micronaut.context.event.StartupEvent;
import io.micronaut.runtime.context.scope.refresh.RefreshEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Singleton
public class PeriodicConfigRefresh {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicConfigRefresh.class);

    private final Environment environment;
    private final ApplicationEventPublisher<RefreshEvent> eventPublisher;
    private boolean started;

    PeriodicConfigRefresh(
            Environment environment,
            ApplicationEventPublisher<RefreshEvent> eventPublisher) {
        this.environment = environment;
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(
            initialDelay = "${micronaut.config.refresh.initial-delay:1m}",
            fixedDelay = "${micronaut.config.refresh.fixed-delay:1m}")
    void refreshConfig() {
        if (started && environment.isRunning()) {
            Map<String, Object> changes = environment.refreshAndDiff();
            LOG.debug("Keys of changed properties: {}", changes.keySet());
            if (!changes.isEmpty()) {
                eventPublisher.publishEvent(new RefreshEvent(changes));
            }
        }
    }

    @EventListener
    void startupEvent(StartupEvent startupEvent) {
        this.started = true;
    }
}
