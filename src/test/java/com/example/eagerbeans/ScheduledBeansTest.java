package com.example.eagerbeans;

import io.micronaut.context.annotation.Property;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;

@MicronautTest
@Property(name = "test.eagerBeans", value = "scheduled")
public class ScheduledBeansTest {
    @Test
    public void test() throws Exception{
        Thread.sleep(10000);
        System.err.println("");
    }
}
