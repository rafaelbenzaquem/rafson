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
    public void testRestPostResponseSuccess() {

        String jsonInput = "{\n"
                + "\"nome\" : \"João da Silva\",\n"
                + "\"email\" : \"joao@gmail.com\",\n"
                + "\"cpfOuCnpj\" : \"77643070253\",\n"
                + "\"tipo\" : 1,\n"
                + "\"telefone1\" : \"997723874\",\n"
                + "\"telefone2\" : \"32547698\",\n"
                + "\"logradouro\" : \"Rua das Acácias\",\n"
                + "\"numero\" : \"345\",\n"
                + "\"complemento\" : \"Apto 302\",\n"
                + "\"bairro\":\"Aldeia\",\n"
                + "\"cep\" : \"38746928\",\n"
                + "\"cidadeId\" : 2\n"
                + "}";
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("POST")
                                .withPath("/clientes")
                                .withBody(jsonInput)
                )
                .respond(
                        response()
                                .withStatusCode(201)
                                .withHeader(
                                        "Location", "http://localhost:1080/clientes/2"
                                )
                );
        Map<String, List<String>> fields = new Rafson().post("http://localhost:1080/clientes", jsonInput);
        for (String key : fields.keySet()) {
            System.out.println("\nPOST KEY = " + key);

            System.out.println("POST HEADER FIELDS = " + fields.get(key).toString());

        }
    }

    @Test
    public void testRestPutResponseSuccess() {

        String jsonInput = "{\n"
                + "\"nome\" : \"João da Silva\",\n"
                + "\"email\" : \"joao@gmail.com\",\n"
                + "\"cpfOuCnpj\" : \"77643070253\",\n"
                + "\"tipo\" : 1,\n"
                + "\"telefone1\" : \"997723874\",\n"
                + "\"telefone2\" : \"32547698\",\n"
                + "\"logradouro\" : \"Rua das Acácias\",\n"
                + "\"numero\" : \"345\",\n"
                + "\"complemento\" : \"Apto 302\",\n"
                + "\"bairro\":\"Aldeia\",\n"
                + "\"cep\" : \"38746928\",\n"
                + "\"cidadeId\" : 2\n"
                + "}";
        new MockServerClient("localhost", 1080)
                .when(
                        request()
                                .withMethod("PUT")
                                .withPath("/clientes/2")
                                .withBody(jsonInput)
                )
                .respond(
                        response()
                                .withStatusCode(204)
                );
        System.out.println(jsonInput);

        Map<String, List<String>> fields = new Rafson().put("http://localhost:1080/clientes/2", jsonInput);
        for (String key : fields.keySet()) {
            System.out.println("\nPUT KEY = " + key);

            System.out.println("PUT HEADER FIELDS = " + fields.get(key).toString());

        }
    }

}
