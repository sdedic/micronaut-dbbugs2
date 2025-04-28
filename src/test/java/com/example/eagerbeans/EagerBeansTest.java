package com.example.eagerbeans;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest(rebuildContext = true)
@Factory
public class EagerBeansTest {
    @Inject
    RegularBean testedRegularBean;

    @Inject
    TestedBean testedBean;

    @Inject
    ApplicationContext context;

    @Singleton
    @Replaces(AppService.class)
    @Bean
    AppService overrideService = new AppService() {
        {
            setValue("override");
        }
    };

    static class RegularBean {
        @Inject
        AppService service;
    }

    /**
     * The test successfully executes, all injections are OK
     * @param testedBean
     */
    @Test
    @Property(name = "test.xx", value = "scheduled")
    public void testRegularBean(TestedBean testedBean) {
        assertNotNull(testedBean);
    }

    /**toString()
     * This method fails on circular dependency, JUST because the enabled bean is eager.
     * Note that the eager bean is not required for tests' construction.
     *
     */
    @Property(name = "test.eagerBeans", value = "context")
    @Test
    public void testContextBean(TestedBean testedBean) {
        assertNotNull(testedBean);
    }

    @Property(name = "test.eagerBeans", value = "scheduled")
    @Test
    public void testScheduledBean(TestedBean testedBean) {
        assertNotNull(testedBean);
    }
}

