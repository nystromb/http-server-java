//package test;
//
//import http.handlers.DirectoryHandler;
//import http.myhandlers.Resource;
//import http.request.Request;
//import http.router.Route;
//import http.router.Router;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.net.URI;
//import java.net.URISyntaxException;
//
//public class RouterTest {
//    Route rootRoute = new Route("/", new DirectoryHandler());
//    Route formRoute = new Route("/form", new Resource());
//
//    @Before
//    public void setUp(){
//        Router.addRoute(rootRoute);
//        Router.addRoute(formRoute);
//    }
//
//    @Test
//    public void test() throws URISyntaxException {
//        Request request = new Request("GET", new URI("/"), "HTTP/1.1");
//
//        Route route = Router.getRoute(request.getPath());
//
//        Assert.assertSame(rootRoute, route);
//    }
//}
