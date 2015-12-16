package test.handlers;

import http.builders.Request;
import http.builders.Response;
import http.LogsHandler;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class LogsHandlerTest {

    @Test
    public void testLogsHandler() throws IOException, URISyntaxException {
        LogsHandler handler = new LogsHandler();
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");
        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        Response response = handler.handle(request);

        assertEquals("HTTP/1.1 200 OK", response.statusLine);
    }
}
