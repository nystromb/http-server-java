package test.Builders;

import http.Builders.Route;
import http.Configuration.Settings;
import http.Handlers.DirectoryHandler;
import http.Handlers.Resource;
import http.Registry.Routes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Created by nystrom on 12/7/15.
 */
public class RouteTest {
    @Before
    public void setUp(){
        Settings.parse(new String[]{"-d", "/Users/nystrom/Documents/cob_spec/public/"});
    }

    @Test
    public void test(){
        Route route = new Route();

        assertFalse(route.isAuthenticated());
    }

    @Test
    public void testSetAuthentication(){
        Route route = new Route();
        route.setAuthentication("admin", "hunter2", "challenge");

        assertTrue(route.isAuthenticated());
    }

    @Test
    public void testSetNextHandler(){
        Route route = new Route();

        route.setNext(new DirectoryHandler());
    }

    @Test
    public void testCustomHandler(){
        Route route = new Route();

        route.setHandler(new Resource());

        assertTrue(route.handler != null);
    }

    @Test
    public void testSomething(){
        Routes routes = new Routes();
        Route ttt = new Route();


        routes.put("/tictactoe", ttt);
        assertTrue(routes.containsKey("/tictactoe"));
    }
}
