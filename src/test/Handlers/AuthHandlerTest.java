package test.Handlers;

import main.Request;
import main.Response;
import main.ServerSettings;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.Handlers.AuthHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by nystrom on 11/24/15.
 */
public class AuthHandlerTest {

    @Before
    public void setUp(){
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/cob_spec/public/"});
        }

    @Test
    public void testWithoutAuthorization() throws URISyntaxException, IOException {
        AuthHandler handler = new AuthHandler("admin:hunter2");
        Request request = new Request("GET", new URI("/some_uri"), "HTTP/1.1");

        Response response = handler.exchange(request);

        assertTrue(response.statusLine.contains("401"));
        assertTrue(response.headers.containsKey("WWW-Authenticate"));
    }

    @Test
    public void testWithAuthorization() throws URISyntaxException, IOException {
        AuthHandler handler = new AuthHandler("admin:hunter2");
        Request request = new Request("GET", new URI("/some_uri"), "HTTP/1.1");

        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        Response response = handler.exchange(request);

        assertTrue(response.statusLine.contains("200"));
    }
}
