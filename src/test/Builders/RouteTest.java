package test.Builders;

import http.Builders.Route;
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
        Route route = new Route();

        assertFalse(route.isAuthenticated());


    }

    @Test
    public void testNotAuthenticated(){
        Route route = new Route().authenticate("user", "password", "secret");

        assertTrue(route.isAuthenticated());
        assertFalse(route.isRedirected());
    }

    @Test
    public void testSetRedirect(){
        Route route = new Route();
        route.setRedirectTo("/");

        assertTrue(route.isRedirected());
    }

    @Test
    public void testAcceptParams(){
        Route route1 = new Route();
        assertFalse(route1.supportsEncoding());

        Route route2 = new Route();
        route2.supportEncoding();

        assertTrue(route2.supportsEncoding());
    }

}
