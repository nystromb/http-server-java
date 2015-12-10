package test.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Handlers.Resource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ResourceTest {
    Resource handler = new Resource();;

    @Before
    public void setUp() throws Exception{

    }

    @Test
    public void testPostSomeData() throws IOException, URISyntaxException{
        Request request = new Request("POST", new URI("/form"), "HTTP/1.1");
        request.addHeader("Content-Length", "9");
        request.setBody("some=data");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("200 OK"));
        assertFalse(new String(response.toByteArray()).contains("some=data"));

        request = new Request("GET", new URI("/form"), "HTTP/1.1");

        response = handler.handle(request);


        assertTrue(response.statusLine.contains("200 OK"));
        assertTrue(new String(response.toByteArray()).contains("some=data"));
    }

    @Test
    public void testPutSomeOtherData() throws IOException, URISyntaxException{
        Request request = new Request("PUT", new URI("/form"), "HTTP/1.1");
        request.addHeader("Content-Length", "14");
        request.setBody("some=otherdata");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("200 OK"));
        request = new Request("GET", new URI("/form"), "HTTP/1.1");

        response = handler.handle(request);

        assertTrue(response.statusLine.contains("200 OK"));
        assertTrue(new String(response.toByteArray()).contains("some=otherdata"));
    }

    @Test
    public void testDeleteTheData() throws IOException, URISyntaxException{
        Request request = new Request("DELETE", new URI("/form"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("200 OK"));

        request = new Request("GET", new URI("/form"), "HTTP/1.1");

        response = handler.handle(request);

        assertTrue(response.statusLine.contains("200 OK"));
    }

    @Test
    public void testOptionsRequestHasAllowHeaders() throws URISyntaxException, IOException {
        Request request = new Request("OPTIONS", new URI("/form"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(response.headers.containsKey("Allow"));
        assertTrue(response.headers.containsValue("GET,HEAD,POST,OPTIONS,PUT"));
    }
}
