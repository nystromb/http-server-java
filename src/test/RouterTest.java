package test;

import main.*;
import main.Handlers.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

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
        Router.addRoute("/parameters", new ParameterDecoder());
        Router.addRoute("/form", new Route());
        Router.addRoute("/partial_content.txt", new FileRangeReader());
        Router.addRoute("/method_options", new Route());
    }

    @Test
    public void testRouterReturnsCustomRouteForForm() throws IOException, URISyntaxException{
        Request request = RequestParser.process("GET /form HTTP/1.1\r\n");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof Route);
    }

    @Test
    public void testRouterReturnsCustomRouteForOptions() throws URISyntaxException {
        Request request = RequestParser.process("OPTIONS /method_options HTTP/1.1\r\n");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof Route);
    }

    @Test
    public void testRouterReturnReadDirectoryAction() throws URISyntaxException{
        Request request = RequestParser.process("GET / HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof DirectoryReader);
    }

    @Test
    public void testReturnsA404HandlerIfRouteNotDefined() throws URISyntaxException{
        Request request = RequestParser.process("GET /foobar HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof Error404);
    }

    @Test
    public void testReturnsAFileReaderIfPathIsAFile() throws URISyntaxException{
        Request request = RequestParser.process("GET /file1 HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof FileContentReader);
    }

    @Test
    public void testReturnsARedirectHandler() throws URISyntaxException{
        Request request = RequestParser.process("GET /redirect HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof RedirectRoute);
    }

    @Test
    public void testReturnsImageFileReaderForJPEG() throws IOException, URISyntaxException{
        Request request = RequestParser.process("GET /image.jpeg HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof ImageFileReader);
    }

    @Test
    public void testReturnsAParameterDecoder() throws URISyntaxException{
        Request request = RequestParser.process("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof ParameterDecoder);
    }

    @Test
    public void testReturnsARangeRequest() throws URISyntaxException{
        Request request = RequestParser.process("GET /partial_content.txt HTTP/1.1\r\nRange: bytes=-4\r\n\r\n");

        RequestHandler handler = Router.route(request);

        assertTrue(handler instanceof FileRangeReader);
    }
}
