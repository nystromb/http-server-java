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
import java.util.Arrays;

/**
 * Created by nystrom on 11/24/15.
 */
public class LogsHandlerTest {

    @Before
    public void setUp(){
        ServerSettings.parse(new String[] {"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});
    }

    @Test
    public void testWithoutAuthorization() throws URISyntaxException, IOException {
        LogsHandler handler = new LogsHandler("admin:hunter2");
        Request request = new Request("GET", new URI("/some_uri"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(new String(response.toByteArray()).contains("401"));
    }

    @Test
    public void testWithAuthorization() throws URISyntaxException, IOException {
        LogsHandler handler = new LogsHandler("admin:hunter2");
        Request request = new Request("GET", new URI("/some_uri"), "HTTP/1.1");

        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        Response response = handler.handle(request);

        assertTrue(new String(response.toByteArray()).contains("200"));
    }
}
