package test.handlers;

import http.Authorization;
import http.builders.Request;
import http.builders.Response;
import org.junit.Before;
import org.junit.Test;
import test.mocks.MockController;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class AuthorizationTest {
    Authorization handler = new Authorization("admin", "hunter2", "secretKey", new MockController());

    @Before
    public void setUp(){

    }

    @Test
    public void testWithoutAuthorization() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("401"));
        assertTrue(response.headers.containsKey("WWW-Authenticate"));
    }

    @Test
    public void testWithAuthorization() throws IOException, URISyntaxException {
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");
        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        Response response = handler.handle(request);

        assertEquals("HTTP/1.1 200 OK", response.statusLine);
    }
}
