package test.Handlers;

import main.Request;
import main.Response;
import main.ServerSettings;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import main.Handlers.LogsHandler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by nystrom on 11/24/15.
 */
public class LogsHandlerTest {

    @Before
    public void setUp(){
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/cob_spec/public/"});
        }

    @Test
    public void testWithoutAuthorization() throws URISyntaxException, IOException {
        LogsHandler handler = new LogsHandler("admin:hunter2");
        Request request = new Request("GET", new URI("/some_uri"), "HTTP/1.1");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).contains("401"));
        assertTrue(new String(response).contains("WWW-Authenticate: Basic realm=\"ServerKey\""));
    }

    @Test
    public void testWithAuthorization() throws URISyntaxException, IOException {
        LogsHandler handler = new LogsHandler("admin:hunter2");
        Request request = new Request("GET", new URI("/some_uri"), "HTTP/1.1");

        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).contains("200"));
    }
}
