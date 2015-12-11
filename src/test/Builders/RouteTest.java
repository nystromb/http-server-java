package test.Builders;

import http.Builders.Request;
import http.Builders.Response;
import http.Builders.Route;
import http.Configuration.Settings;
import http.Handlers.Authorization;
import http.Handlers.DirectoryHandler;
import http.Registry.Routes;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by nystrom on 12/11/15.
 */
public class RouteTest {
    Routes routes = new Routes();

    @Before
    public void setUp(){
        Settings.parse(new String[] { "-d", "/Users/nystrom/Documents/cob_spec/public/"});
        routes.put("/", new Route(new DirectoryHandler()));
        routes.put("/logs", new Route(new Authorization("admin", "hunter2", "secret", new DirectoryHandler())));
    }

    @Test
    public void testCreateASingleChain() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");
        Route route = routes.get("/");

        Response response = route.handle(request);

        assertTrue(response.statusLine.contains("200 OK"));
    }

    @Test
    public void testUnauthorizedChain() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");
        Route route = routes.get("/logs");

        Response response = route.handle(request);

        assertTrue(response.statusLine.contains("401 Unauthorized"));
    }

    @Test
    public void testAuthorizedChain() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");
        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        Route route = routes.get("/logs");
        Response response = route.handle(request);

        assertTrue(response.statusLine.contains("200 OK"));
    }
}
