package com.example.configrefresh;

import io.micronaut.configuration.jdbc.hikari.DatasourceConfiguration;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.BeanRegistration;
import io.micronaut.context.annotation.Context;
import io.micronaut.context.annotation.Requires;
import io.micronaut.runtime.context.scope.refresh.RefreshEvent;
import io.micronaut.runtime.event.annotation.EventListener;
import io.micronaut.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Context
@Requires(property = "configrefresh")
public class PeriodicPoll {
    private static final Logger LOG = LoggerFactory.getLogger(PeriodicPoll.class);
    private final ApplicationContext applicationContext;
    private final DatasourceConfiguration datasourceConfiguration;


    public PeriodicPoll(ApplicationContext applicationContext, DatasourceConfiguration datasourceConfiguration) {
        this.applicationContext = applicationContext;
        this.datasourceConfiguration = datasourceConfiguration;
    }

    @Scheduled(fixedDelay = "30s")
    public void printPropertiesAndCredentials() {
        LOG.info("Password property: {}", applicationContext.getProperty("datasources.default.password", String.class));
        LOG.info("Datasource property: {}", datasourceConfiguration.getCredentials().getPassword());
    }

    @EventListener
    public void onRefresh(RefreshEvent event) {
        LOG.info("Got refresh event: {}", event);
        LOG.info("Refreshed password property: {}", applicationContext.getProperty("datasources.default.password", String.class));
        LOG.info("Datasource property after refresh: {}", datasourceConfiguration.getCredentials().getPassword());

        BeanRegistration reg = applicationContext.findBeanRegistration(datasourceConfiguration).orElse(null);
        if (reg != null) {
            applicationContext.refreshBean(reg);
            LOG.info("Datasource property after explicit refreshBean: {}", datasourceConfiguration.getCredentials().getPassword());
        }
    }
}
