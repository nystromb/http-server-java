package test.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Configuration.Settings;
import http.Handlers.AuthHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by nystrom on 11/24/15.
 */
public class AuthHandlerTest {

    @Before
    public void setUp() {
        Settings.parse(new String[]{"-d", "/Users/nystrom/Documents/cob_spec/public/"});
    }

    @Test
    public void testWithoutAuthorization() throws URISyntaxException, IOException {
        AuthHandler handler = new AuthHandler("admin", "hunter2", "secretKey");
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("401"));
        assertTrue(response.headers.containsKey("WWW-Authenticate"));
    }
}
