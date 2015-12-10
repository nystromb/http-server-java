package test.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Configuration.Settings;
import http.Handlers.LogsHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

/**
 * Created by nystrom on 12/10/15.
 */
public class LogsHandlerTest {
    @Before
    public void setUp(){
        Settings.parse(new String[]{ "-d", "/Users/nystrom/Documents/cob_spec/public/"});
    }

    @Test
    public void test() throws IOException, URISyntaxException {
        LogsHandler handler = new LogsHandler();
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");
        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        Response response = handler.handle(request);

        assertEquals("HTTP/1.1 200 OK", response.statusLine);
    }
}
