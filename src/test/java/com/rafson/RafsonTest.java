package com.rafson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockserver.client.server.MockServerClient;
import org.mockserver.integration.ClientAndServer;

import static org.junit.Assert.assertEquals;
import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

public class RafsonTest {

    //    private ClientAndProxy proxy;
    private ClientAndServer mockServer;

    private MockServerClient mockServerClient;


    @Before
    public void startProxy() {
        mockServer = startClientAndServer(1080);
//        proxy = startClientAndProxy(1090);
        mockServerClient = new MockServerClient("localhost", 1080);
    }

    @After
    public void stopProxy() {
//        proxy.stop();
        mockServer.stop();
    }

    @Test
    public void httpMethodGetResponseSuccessTest() {
        String expected = "{\"teste\":\"teste\"}";

        mockServerClient
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

        Response response = Rafson.get("http://localhost:1080/test");
        String actual = response.getBody();
        assertEquals(expected, actual);

        String actual2 = response.getHeader().get("code").get(0);
        assertEquals("HTTP/1.1 200 OK", actual2);
    }

    @Test
    public void httpMethodHeadResponseSuccessTest() {
        mockServerClient
                .when(
                        request()
                                .withMethod("HEAD")
                                .withPath("/test")
                )
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeader(
                                        "Location", "http://localhost"
                                )
                );
        Response response = Rafson.head("http://localhost:1080/test");

        String expected2 = response.getHeader().get("code").get(0);
        assertEquals("HTTP/1.1 200 OK", expected2);

        String expected = response.getHeader().get("Location").get(0);
        assertEquals("http://localhost", expected);
    }

    @Test
    public void httpMethodPostResponseSuccessTest() {

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
        mockServerClient
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
        Response response = Rafson.post("http://localhost:1080/clientes", jsonInput);
        String expected = response.getHeader().get("code").get(0);
        assertEquals("HTTP/1.1 201 Created", expected);
    }

    @Test
    public void httpMethodPutResponseSuccessTest() {

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
        mockServerClient
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

        Response response = Rafson.put("http://localhost:1080/clientes/2", jsonInput);
        String expected = response.getHeader().get("code").get(0);
        assertEquals("HTTP/1.1 204 No Content", expected);
    }


    @Test
    public void httpMethodDeleteResponseSuccessTest() {

        mockServerClient
                .when(
                        request()
                                .withMethod("DELETE")
                                .withPath("/client/2")
                )
                .respond(
                        response()
                                .withStatusCode(204)
                );

        Response response = Rafson.delete("http://localhost:1080/client/2");
        String expected = response.getHeader().get("code").get(0);
        assertEquals("HTTP/1.1 204 No Content", expected);

    }

}
