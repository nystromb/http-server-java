package test.Handlers;

import main.Handlers.RedirectHandler;
import main.Request;
import main.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 12/4/15.
 */
public class RedirectHandlerTest {
    RedirectHandler handler = new RedirectHandler();

    @Before
    public void setUp(){

    }

    @Test
    public void testReturns302Status() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/redirect"), "HTTP/1.1");

        Response response = handler.exchange(request);

        assertTrue(response.status.contains("302 Found"));
        assertTrue(response.headers.containsKey("Location"));
        assertTrue(response.headers.containsValue("http://localhost:5000/"));
    }
}