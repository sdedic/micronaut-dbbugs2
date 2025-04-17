package com.example.replacement;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;

/**
 * Demonstrates that TYPES annotated by Scope can be replaced with beans with OTHER scope
 */
@Prototype
@Replaces(ProtoBean.class)
@Requires(property = "replacement.singletonProtoBean")
public class ReplacementProtoBean extends ProtoBean{
}
