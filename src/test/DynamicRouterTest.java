package test;

import main.*;
import main.Builders.Request;
import main.Builders.Route;
import main.Handlers.*;
import main.Handlers.FileHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 11/5/15.
 */
public class DynamicRouterTest {
    @Before
    public void setUp() {
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});
    }
    @After
    public void after(){
        DynamicRouter.routes.clear();
    }
    @Test
    public void testRootReturnsDirectoryHandler() throws URISyntaxException {
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");

        Route route= DynamicRouter.buildRoute(request);

        assertTrue(route.handlers.get(0) instanceof DirectoryHandler);
    }

    @Test
    public void testAddsParamHandlerToChain() throws URISyntaxException {
        Request request = new Request("GET", new URI("/?q=data"), "HTTP/1.1");

        Route route = DynamicRouter.buildRoute(request);

        assertTrue(route.handlers.get(0) instanceof ParameterHandler);
        assertTrue(route.handlers.get(1) instanceof DirectoryHandler);
    }

    @Test
    public void test() throws URISyntaxException {
        Request request = new Request("GET", new URI("/file1?q=1"), "HTTP/1.1");

        Route route = DynamicRouter.buildRoute(request);

        assertFalse(route.isAuthenticated());
        assertEquals(ParameterHandler.class, route.handlers.get(0).getClass());
        assertTrue(route.handlers.get(1) instanceof FileHandler);
    }

    @Test
    public void testAddsAuthforAuthorizedRoute() throws URISyntaxException {
        Route newRoute = new Route();
        newRoute.setAuthentication("admin", "Hunter2", "default");
        DynamicRouter.addRoute("/logs", newRoute);

        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");

        Route route = DynamicRouter.buildRoute(request);
        assertTrue(route.handlers.get(0) instanceof AuthHandler);
        assertTrue(route.handlers.get(1) instanceof DirectoryHandler);
    }

    @Test
    public void testAddsFileHandlerWithAuth() throws URISyntaxException {
        Route newRoute = new Route();
        newRoute.setAuthentication("admin", "Hunter2", "default");
        DynamicRouter.addRoute("/some/file", newRoute);

        Request request = new Request("GET", new URI("/some/file"), "HTTP/1.1");

        Route route = DynamicRouter.buildRoute(request);
        assertTrue(route.handlers.get(0) instanceof AuthHandler);
//        assertTrue(route.handlers.get(1) instanceof NotFoundHandler);
    }

    @Test
    public void testAddsFileHandlerWithAuthAndParams() throws URISyntaxException {
        Route newRoute = new Route();
        newRoute.setAuthentication("admin", "Hunter2", "default");
        DynamicRouter.addRoute("/file400", newRoute);

        Request request = new Request("GET", new URI("/file400?q=1"), "HTTP/1.1");

        Route route = DynamicRouter.buildRoute(request);
        assertTrue(route.handlers.get(0) instanceof AuthHandler);
        assertTrue(route.handlers.get(1) instanceof ParameterHandler);
//        assertTrue(route.handlers.get(2) instanceof NotFoundHandler);
    }

    @Test
    public void testImageRoute() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.png"), "HTTP/1.1");

        Route route = DynamicRouter.buildRoute(request);

        assertTrue(route.handlers.get(0) instanceof FileHandler);
    }

    @Test
    public void testRoute() throws URISyntaxException, IOException {
        Route newRoute = new Route();
        newRoute.setAuthentication("admin", "Hunter2", "default");
        DynamicRouter.addRoute("/file1", newRoute);

        Request request = new Request("GET", new URI("/file1?q=1"), "HTTP/1.1");

        Route route = DynamicRouter.buildRoute(request);
        assertTrue(DynamicRouter.route(route, request).statusLine.contains("401"));
    }

    @Test
    public void testParameters() throws URISyntaxException {
        Request request = new Request("GET", new URI("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff"), "HTTP/1.1");
        Route route = DynamicRouter.buildRoute(request);

        assertTrue(route.handlers.get(0) instanceof ParameterHandler);
        assertTrue(route.handlers.size() == 1);
    }
}
