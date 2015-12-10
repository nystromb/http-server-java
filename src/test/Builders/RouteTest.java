package test.Builders;

import http.Builders.Route;
import http.Handlers.Resource;
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

    }

    @Test
    public void testIsAuthenticated(){
        Route route = new Route(new Resource());

        assertFalse(route.isAuthenticated());
    }

    @Test
    public void testNotAuthenticated(){
        Route route = new Route(new Resource()).authenticate("user", "password", "secret");

        assertTrue(route.isAuthenticated());
        assertFalse(route.isRedirected());
    }

    @Test
    public void testAcceptParams(){
        Route route1 = new Route(new Resource());;
        assertFalse(route1.supportsEncoding());

        Route route2 = new Route(new Resource());;
        route2.supportEncoding();

        assertTrue(route2.supportsEncoding());
    }

}
