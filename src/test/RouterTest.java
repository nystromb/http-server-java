package test;

import main.*;
import main.Handlers.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 11/5/15.
 */
public class RouterTest {
    @Before
    public void setUp(){
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});
        Router.addRoute("/", new DirectoryReader());
        Router.addRoute("/file1", new FileContentReader());
        Router.addRoute("/image.jpeg", new ImageFileReader());
        Router.addRoute("/redirect", new RedirectRoute());
        Router.addRoute("/form", new Route());
        Router.addRoute("/method_options", new Route());
    }

    @Test
    public void testRouterReturnsCustomRouteForForm() throws IOException{
        Request request = RequestParser.process("GET /form HTTP/1.1\r\n");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof Route);
    }

    @Test
    public void testRouterReturnsCustomRouteForOptions(){
        Request request = RequestParser.process("OPTIONS /method_options HTTP/1.1\r\n");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof Route);
    }

    @Test
    public void testRouterReturnReadDirectoryAction(){
        Request request = RequestParser.process("GET / HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof DirectoryReader);
    }

    @Test
    public void testReturnsA404HandlerIfRouteNotDefined(){
        Request request = RequestParser.process("GET /foobar HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof Error404);
    }

    @Test
    public void testReturnsAFileReaderIfPathIsAFile(){
        Request request = RequestParser.process("GET /file1 HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof FileContentReader);
    }

    @Test
    public void testReturnsARedirectHandler(){
        Request request = RequestParser.process("GET /redirect HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof RedirectRoute);
    }

    @Test
    public void testReturnsImageFileReaderForJPEG() throws IOException{
        Request request = RequestParser.process("GET /image.jpeg HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof ImageFileReader);
    }
}
