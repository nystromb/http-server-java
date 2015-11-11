package test;

import main.Router;
import main.ServerSettings;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 11/5/15.
 */
public class RouterTest {
    @Test
    public void testAddRoutes(){
        Router.addResource("/");

        assertTrue(Router.hasPath("/"));
    }

    @Test
    public void testPathIsNotValid(){
        assertFalse(Router.hasPath("/foobar"));
    }
}
