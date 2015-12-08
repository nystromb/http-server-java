package test.RouterTest;

import main.Builders.Request;
import main.Builders.Route;
import main.Configuration.ServerSettings;
import main.Handlers.*;
import main.Handlers.FileHandler;
import main.Router.Router;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 11/5/15.
 */
public class RouterTest {
    @Before
    public void setUp() {
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});
    }

    @Test
    public void testRootReturnsDirectoryHandler() throws URISyntaxException {
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");

        Route route= Router.buildRoute(request);

        assertTrue(route.handlers.get(0) instanceof DirectoryHandler);
    }

    @Test
    public void testAddsParamHandlerToChain() throws URISyntaxException {
        Request request = new Request("GET", new URI("/?q=data"), "HTTP/1.1");

        Route route = Router.buildRoute(request);

        assertTrue(route.handlers.get(0) instanceof ParameterHandler);
        assertTrue(route.handlers.get(1) instanceof DirectoryHandler);
    }

    @Test
    public void test() throws URISyntaxException {
        Request request = new Request("GET", new URI("/file1?q=1"), "HTTP/1.1");

        Route route = Router.buildRoute(request);

        assertFalse(route.isAuthenticated());
        assertEquals(ParameterHandler.class, route.handlers.get(0).getClass());
        assertTrue(route.handlers.get(1) instanceof FileHandler);
    }

    @Test
    public void testImageRoute() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.png"), "HTTP/1.1");

        Route route = Router.buildRoute(request);

        assertTrue(route.handlers.get(0) instanceof FileHandler);
    }
}
