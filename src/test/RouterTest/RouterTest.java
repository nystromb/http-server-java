package test.RouterTest;

import http.Builders.Request;
import http.Builders.Response;
import http.Configuration.Settings;
import http.Router.Router;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

/**
 * Created by nystrom on 11/5/15.
 */
public class RouterTest {
    Router router = new Router();

    @Before
    public void setUp() {
        Settings.parse(new String[]{"-d", "/Users/Documents/cob_spec/public"});
    }

    @Test
    public void testFirstChecksFor404ForUndefinedRoute() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/some/undefined/route"), "HTTP/1.1");

        router.buildRoute(request);
        Response response = router.handle(request);

        assertEquals("HTTP/1.1 404 Not Found", response.statusLine);
    }

    @Test
    public void testForSuccess() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/tictactoe"), "HTTP/1.1");

        router.buildRoute(request);
        Response response = router.handle(request);

        assertEquals("HTTP/1.1 200 OK", response.statusLine);
    }
}
