package com.example.racebug;

import com.example.configrefresh.PeriodicPoll;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.event.BeanDestroyedEvent;
import io.micronaut.context.event.BeanDestroyedEventListener;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.runtime.context.scope.refresh.RefreshEvent;
import io.micronaut.runtime.context.scope.refresh.RefreshEventListener;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@Context
public class EagerTrigger implements BeanDestroyedEventListener<RacebugRefreshSingleton>, RefreshEventListener {
    private static final Logger LOG = LoggerFactory.getLogger(EagerTrigger.class);
    private final EagerService service;
    private final ApplicationContext context;
    private final RacebugConfig config;

    public EagerTrigger(EagerService service, ApplicationContext context, RacebugConfig config) {
        this.service = service;
        this.context = context;
        this.config = config;
        LOG.info("Initial racebug name: {}", service.getFreshName());
    }

    @Override
    public void onDestroyed(@NonNull BeanDestroyedEvent<RacebugRefreshSingleton> event) {
        LOG.info("Reloaded racebug property name DURING refresh: {}", context.getProperty("racebugConfig.name", String.class));
        LOG.info("Racebug name DURING refresh: initial {}, fresh {}", service.getInitialName(), service.getFreshName());
    }

    @Override
    public @NonNull Set<String> getObservedConfigurationPrefixes() {
        return Set.of("racebug-config");
    }

    @Override
    public void onApplicationEvent(RefreshEvent event) {
        LOG.info("Reloaded racebug property nameAFTER refresh: {}", context.getProperty("racebugConfig.name", String.class));
        LOG.info("Reloaded racebug CONFIG name AFTER refresh: {}", config.getName());
        LOG.info("Racebug name AFTER refresh initial: {}, fresh: {}", service.getInitialName(), service.getFreshName());
    }
}
