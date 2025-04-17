package com.example.clientfilter;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MutableHttpRequest;
import io.micronaut.http.annotation.ClientFilter;
import io.micronaut.http.annotation.RequestFilter;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;


@ClientFilter
@MicronautTest
public class ClientFilterOverflow {

    @Client("/")
    @Inject
    private HttpClient client;

    @RequestFilter
    public void decorate(MutableHttpRequest<?> req) {
        System.err.println();
    }

    @Test
    public void testStackOverflow() {
        HttpRequest req = HttpRequest.GET("/");
        client.toBlocking().exchange(req);
    }
}
