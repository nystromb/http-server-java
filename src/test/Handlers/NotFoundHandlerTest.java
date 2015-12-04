package test.Handlers;

import main.Handlers.NotFoundHandler;
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
public class NotFoundHandlerTest {
    NotFoundHandler handler;

    @Before
    public void setUp(){
        handler = new NotFoundHandler();
    }

    @Test
    public void test() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/foobar"), "HTTP/1.1");

        Response response = handler.getResponse(request);

        assertTrue(new String(response.toByteArray()).contains("404"));
    }
}
