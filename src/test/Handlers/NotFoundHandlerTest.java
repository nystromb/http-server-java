package test.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Handlers.NotFoundHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;

/**
 * Created by nystrom on 12/4/15.
 */
public class NotFoundHandlerTest {
    NotFoundHandler handler;

    @Before
    public void setUp(){
        handler = new NotFoundHandler();
    }

    @Test
    public void test() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/foobar"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("404 Not Found"));
    }
}
