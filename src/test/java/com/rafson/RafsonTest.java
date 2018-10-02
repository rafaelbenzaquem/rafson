package com.rafson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndProxy;
import org.mockserver.integration.ClientAndServer;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockserver.integration.ClientAndProxy.startClientAndProxy;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

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

    @Test
    public void testRestHeadResponseSuccess() {
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("GET")
                                .withPath("/test")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader(
                                        "Location", "http://localhost"
                                )
                );

        Map<String, List<String>> headerField = new Rafson().head("http://localhost:1080/test");
        String expected = headerField.get("Location").get(0);
        assertEquals(expected, "http://localhost");

    }

}
