package com.example.racebug;

import io.micronaut.runtime.context.scope.Refreshable;
import jakarta.inject.Provider;
import jakarta.inject.Singleton;

import java.util.concurrent.atomic.AtomicInteger;

@Refreshable("racebug-config")
public class RacebugRefreshSingleton {
    private final RacebugConfig racebugConfig;
    private final Provider<EagerTrigger> triggerProvider;
    private final String initialName;
    private final int id;

    private static final AtomicInteger counter = new AtomicInteger(0);

    public RacebugRefreshSingleton(RacebugConfig racebugConfig, Provider<EagerTrigger> triggerProvider) {
        this.racebugConfig = racebugConfig;
        this.triggerProvider = triggerProvider;
        this.initialName = racebugConfig.getName();
        this.id = counter.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public String getInitialName() {
        return initialName;
    }

    public String getRefreshableName() {
        return racebugConfig.getName();
    }
}
