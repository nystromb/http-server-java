package test.Builders;

import main.*;
import main.Builders.Route;
import main.Handlers.DirectoryHandler;
import main.Handlers.Resource;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


/**
 * Created by nystrom on 12/7/15.
 */
public class RouteTest {
    @Before
    public void setUp(){
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/cob_spec/public/"});
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
}
