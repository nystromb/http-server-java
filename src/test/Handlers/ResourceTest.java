package test.Handlers;

import static org.junit.Assert.*;

import main.*;

import main.Handlers.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ResourceTest {
    Resource handler;

    @Before
    public void setUp() throws Exception{
        Router.addRoute("/form", new Resource());
        handler = new Resource();
    }

    @Test
    public void testPostThenGetFormData() throws IOException, URISyntaxException{
        //POST /form
        Request request = new Request("POST", new URI("/form"), "HTTP/1.1");
        request.addHeader("Content-Length", "9");
        request.setBody("some=data");

        Response response = handler.getResponse(request);

        assertTrue(new String(response.toByteArray()).contains("200 OK"));
        assertFalse(new String(response.toByteArray()).contains("some=data"));

        //GET /form
        request = new Request("GET", new URI("/form"), "HTTP/1.1");

        response = handler.getResponse(request);

        assertTrue(new String(response.toByteArray()).contains("200 OK"));
        assertTrue(new String(response.toByteArray()).contains("some=data"));
    }

    @Test
    public void testPutThenGetFormController() throws IOException, URISyntaxException{
        //PUT /form
        Request request = new Request("PUT", new URI("/form"), "HTTP/1.1");
        request.addHeader("Content-Length", "14");
        request.setBody("some=otherdata");

        Response response = handler.getResponse(request);

        assertTrue(new String(response.toByteArray()).contains("200 OK"));

        //GET /form
        request = new Request("GET", new URI("/form"), "HTTP/1.1");

        response = handler.getResponse(request);

        assertTrue(new String(response.toByteArray()).contains("200 OK"));
        assertTrue(new String(response.toByteArray()).contains("some=otherdata"));
    }

    @Test
    public void testDeleteThenGetFormController() throws IOException, URISyntaxException{
        //DELETE /form
        Request request = new Request("DELETE", new URI("/form"), "HTTP/1.1");

        Response response = handler.getResponse(request);

        assertTrue(new String(response.toByteArray()).contains("200 OK"));

        //GET /form
        request = new Request("GET", new URI("/form"), "HTTP/1.1");

        response = handler.getResponse(request);

        assertTrue(new String(response.toByteArray()).contains("200 OK"));
        assertTrue(new String(response.getBody()).isEmpty());
    }

    @Test
    public void testOptionsRequest() throws URISyntaxException, IOException {
        Request request = new Request("OPTIONS", new URI("/form"), "HTTP/1.1");

        Response response = handler.getResponse(request);

        assertTrue(new String(response.toByteArray()).contains("Allow: GET,HEAD,POST,OPTIONS,PUT"));
    }

}
