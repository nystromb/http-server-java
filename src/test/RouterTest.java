package test;

import main.*;
import main.Handlers.*;
import main.Handlers.FileHandler;
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
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});
        Main.buildRoutes();
    }

    @Test
    public void testRouterReturnReadDirectoryAction() throws URISyntaxException{
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof DirectoryHandler);
    }

    @Test
    public void testRouterReturnsAFileAction() throws URISyntaxException{
        Request request = new Request("GET", new URI("/file1"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testRouterReturnsAFileActionForFile2() throws URISyntaxException{
        Request request = new Request("GET", new URI("/file2"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testRouterReturnsFileHandlerForTxtFiles() throws URISyntaxException {
        Request request = new Request("GET", new URI("/text-file.txt"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testRouterReturnsFileHandlerForPNGFiles() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.png"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testRouterReturnsFileHandlerForJPEGFiles() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.jpeg"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testRouterReturnsFileHandlerForImageFiles() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.gif"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testReturnsAAuthHandlerForLogs() throws URISyntaxException {
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof LogsHandler);
    }

    @Test
    public void testReturnsError404IfFoobar() throws URISyntaxException {
        Request request = new Request("GET", new URI("/foobar"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof NotFoundHandler);
    }

    @Test
    public void testReturnsBasicResource() throws URISyntaxException{
        Request request = new Request("GET", new URI("/form"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof Resource);
    }

    @Test
    public void testReturnsRedirect() throws URISyntaxException {
        Request request = new Request("GET", new URI("/redirect"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof RedirectHandler);
    }

    @Test
    public void testReturnsParameterHandler() throws URISyntaxException {
        Request request = new Request("GET", new URI("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff"), "HTTP/1.1");

        Requestable handler = Router.getHandler(request);

        assertTrue(handler instanceof ParameterHandler);
    }
}
