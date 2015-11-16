package test;

import main.*;
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
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/my-8thlight-apprenticeship/cob_spec/public/"});
        Router.addRoute("/form", new CustomRoute());
        Router.addRoute("/method_options", new CustomRoute());
    }

    @Test
    public void testRouterReturnsCustomRouteForForm() throws IOException{
        Request request = RequestParser.process("GET /form HTTP/1.1\r\n");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof CustomRoute);
    }

    @Test
    public void testRouterReturnsCustomRouteForOptions(){
        Request request = RequestParser.process("OPTIONS /method_options HTTP/1.1\r\n");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof CustomRoute);
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

    public void testReturnsAFileReaderIfPathIsAFile(){
        Request request = RequestParser.process("GET /file HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof FileContentReader);
    }
}
