package test.RouterTest;

import http.Builders.Request;
import http.Builders.Route;
import http.Configuration.Settings;
import http.Handlers.DirectoryHandler;
import http.Handlers.ParameterHandler;
import http.Handlers.TicTacToeHandler;
import http.Handlers.FileHandler;
import http.Router.Router;
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
        Settings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});
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

    @Test
    public void testTicTacToe() throws URISyntaxException {
        Request request = new Request("GET", new URI("/tictactoe"), "HTTP/1.1");

        Route route = Router.buildRoute(request);

        assertTrue(route.handlers.get(0) instanceof TicTacToeHandler);
    }
//
//    @Test
//    public void testTicTacToeWithUrlParams() throws URISyntaxException {
//        Request request = new Request("POST", new URI("/tictactoe/1"), "HTTP/1.1");
//
//        Route route = Router.buildRoute(request);
//
//        assertTrue(route.handlers.get(0) instanceof TicTacToeHandler);
//    }
}
