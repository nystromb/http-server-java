package test.handlers;

import http.RedirectHandler;
import http.builders.Request;
import http.builders.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class RedirectHandlerTest {
    @Before
    public void setUp(){

    }

    @Test
    public void testReturns302statusLineLine() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/redirect"), "HTTP/1.1");

        Response response = new RedirectHandler("/").handle(request);

        assertTrue(response.statusLine.contains("302 Found"));
        assertTrue(response.headers.containsKey("Location"));
        assertTrue(response.headers.containsValue("http://localhost:5000/"));
    }
}
