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

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).contains("302"));
        assertTrue(new String(response).contains("Location: http://localhost:5000/"));
    }
}
