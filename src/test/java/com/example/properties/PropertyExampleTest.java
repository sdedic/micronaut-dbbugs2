package com.example.properties;

import io.micronaut.context.ApplicationContext;
import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.micronaut.test.support.TestPropertyProvider;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
@Property(name = "test.literal", value = "aa")
@Property(name = "test.literal", value = "bb")
@Property(name = "test.expression", value = "#{ 1 + 2 }")
public class PropertyExampleTest implements TestPropertyProvider  {
    @Value("#{ T(Math).random() }")
    String computedRandom;

    @ConfigurationProperties("test")
    static class ConfigurableBean {
        String expression;
        String expression2;
        String literal;
        String barExpression;

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public String getExpression2() {
            return expression2;
        }

        public void setExpression2(String expression2) {
            this.expression2 = expression2;
        }
    }

    @Inject
    ConfigurableBean injectableBean;

    /**
     * This expression is malformed, yet no warning/error is reported; it should be as the injection is clearly wrong.
     */
    @Value("${test.expression")
    String malformedExpression;

    /**
     * No ${} reference, so value is injected as literal
     */
    @Value("test.expression")
    String notInterpolated;

    /**
     * Value is interpolated, BUT the propery should be computed.
     */
    @Value("${test.expression}")
    String interpolatedProperty;

    @Value("#{1 + 2 }")
    String evaluatedExpression;

    /**
     * Should be injected as a literal
     */
    @Value("test.literal")
    String uninterpolatedLieral;

    /**
     * Literal is interpolated
     */
    @Value("${test.literal}")
    String interpolatedLiteral;

    @Inject
    ValueBean valueBean;

    @Inject
    ApplicationContext context;

    boolean getPropertiesCalled;

    /**
     * Proves that field injection evaluates expressions.
     */
    @Test
    public void testExpressionField() {
        assertNotNull(computedRandom);
    }

    /**
     * Checks that expression  works for @Property annotation
     */
    @Test
    public void testPropertyExpressionWorks() {
        assertEquals(evaluatedExpression, interpolatedProperty);
    }

    /**
     * This test fails, although the same syntax is used to make field injection. Check values of the ConfigurableBean
     * and ValueBean for consistency
     * @param val
     */
    @Test
    public void testExpressionParameter(@Value("#{ T(Math).random() }") String val) {
        assertNotNull(val);
    }

    /**
     * Unlike the same syntax for field injection, this INTERPOLATES a property
     * @param expr
     */
    @Test
    public void testProperty1(@Value("test.expression") String expr) {
        assertEquals(this.notInterpolated, expr);
    }

    /**
     * This should succeed, as the same syntax is used successfuly for field injection
     */
    @Test
    public void testProperty2(@Value("${test.literal}") String expr) {

    }

    /**
     * The test class implements TestPropertyProvider, but its getProperties() is never called
     */
    @Test
    public void testGetPropertiesCalled() {
        assertTrue(getPropertiesCalled);
    }

    @Override
    public @NonNull Map<String, String> getProperties() {
        this.getPropertiesCalled = true;
        return Map.of();
    }
}
