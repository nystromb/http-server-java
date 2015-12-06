package test.Handlers;

import main.Handlers.ParameterHandler;
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
public class ParameterHandlerTest {
    ParameterHandler handler = new ParameterHandler();

    @Before
    public void setUp(){

    }

    @Test
    public void testReturnsOK() throws IOException, URISyntaxException {
        Request request = new Request("GET", new URI("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff"), "HTTP/1.1");

        Response response = handler.exchange(request);

        assertTrue(response.status.contains("200 OK"));
    }

    @Test
    public void testIfReturnsDecodedParams() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff"), "HTTP/1.1");

        Response response = handler.exchange(request);

        assertTrue(new String(response.toByteArray()).contains("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?"));
        assertTrue(new String(response.toByteArray()).contains("variable_2 = stuff"));

    }

}
