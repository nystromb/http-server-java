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
    public void setUp(){
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});

        Router.addRoute("/logs", new LogsHandler(""));
    }

    @Test
    public void testRouterReturnReadDirectoryAction() throws URISyntaxException{
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");

        RequestHandler handler = Router.getHandler(request);

        assertTrue(handler instanceof DirectoryHandler);
    }

    @Test
    public void testRouterReturnsAFileAction() throws URISyntaxException{
        Request request = new Request("GET", new URI("/file1"), "HTTP/1.1");

        RequestHandler handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testRouterReturnsFileHandlerForTxtFiles() throws URISyntaxException {
        Request request = new Request("GET", new URI("/text-file.txt"), "HTTP/1.1");

        RequestHandler handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testRouterReturnsFileHandlerForPNGFiles() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.png"), "HTTP/1.1");

        RequestHandler handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testRouterReturnsFileHandlerForJPEGFiles() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.jpeg"), "HTTP/1.1");

        RequestHandler handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testRouterReturnsFileHandlerForImageFiles() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.gif"), "HTTP/1.1");

        RequestHandler handler = Router.getHandler(request);

        assertTrue(handler instanceof FileHandler);
    }

    @Test
    public void testReturnsAAuthHandlerForLogs() throws URISyntaxException {
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");

        RequestHandler handler = Router.getHandler(request);

        assertTrue(handler instanceof LogsHandler);
    }
}
