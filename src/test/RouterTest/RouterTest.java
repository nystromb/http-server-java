package test.RouterTest;

import http.Builders.Request;
import http.Builders.Response;
import http.Builders.Route;
import http.Registry.Routes;
import http.Router.Router;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nystrom on 11/5/15.
 */
public class RouterTest {
    Routes routes = new Routes();
    Route route;

    @Before
    public void setUp() {
        route = new Route();

        routes.put("/defined/route",route);
    }

    @Test
         public void testFirstChecksFor404ForUndefinedRoute() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/some/random/route"), "HTTP/1.1");
        Router router = new Router();
        router.setNext(route.getAuth());

        Response response = router.route(request);

        assertTrue(response.statusLine.contains("404"));
    }

    @Test
    public void test() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/defined/route"), "HTTP/1.1");
        Router router = new Router();
        router.setNext(route.handler);

        Response response = router.route(request);

        assertEquals("HTTP/1.1 200 OK", response.statusLine);
    }
}
