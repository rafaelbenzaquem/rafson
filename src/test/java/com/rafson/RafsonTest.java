package com.rafson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;

import static org.junit.Assert.*;
import static org.mockserver.integration.ClientAndProxy.*;
import static org.mockserver.integration.ClientAndServer.*;
import static org.mockserver.model.HttpRequest.*;
import static org.mockserver.model.HttpResponse.*;
import static org.mockserver.model.JsonBody.*;

public class RafsonTest {

    private ClientAndProxy proxy;
    private ClientAndServer mockServer;


    @Before
    public void startProxy() {
        mockServer = startClientAndServer(1080);
        proxy = startClientAndProxy(1090);

    }

    @After
    public void stopProxy() {
        proxy.stop();
        mockServer.stop();
    }

    @Test
    public void testRestGetResponseSuccess() {
        String expected = "{\"teste\":\"teste\"}";

        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/test")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withBody(json(expected))
                                .withHeader(
                                        "Location", "http://localhost"
                                )
                );

        String actual = new Rafson().get("http://localhost:1080/test");
        assertEquals(expected, actual);

    }

}
