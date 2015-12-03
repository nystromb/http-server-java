package test.Handlers;

import main.Handlers.FileHandler;
import main.Handlers.RequestHandler;
import main.Request;
import main.Response;
import main.ServerSettings;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 12/3/15.
 */
public class FileHandlerTest {
    FileHandler handler;

    @Before
    public void setUp() throws IOException {
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});
        handler = new FileHandler();
    }

    @Test
    public void testIsARequestHandler(){
        assertTrue(handler instanceof RequestHandler);
    }

    @Test
    public void testReturns200OK() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/file1"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(new String(response.toByteArray()).contains("200 OK"));
    }

    @Test
    public void testReturnsFile1Contents() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/file1"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(new String(response.toByteArray()).contains("file1 contents"));
    }

    @Test
    public void testWorksWithPNGFiles() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/image.png"), "HTTP/1.1");

        Response response = handler.handle(request);

        byte[] expectedImageBytes = Files.readAllBytes(new File(ServerSettings.getRootDirectory(), "/image.png").toPath());
        assertArrayEquals(expectedImageBytes, response.getBody());
    }

    @Test
    public void testWorksWithJPEGFiles() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/image.jpeg"), "HTTP/1.1");

        Response response = handler.handle(request);

        byte[] expectedImageBytes = Files.readAllBytes(new File(ServerSettings.getRootDirectory(), "/image.jpeg").toPath());
        assertArrayEquals(expectedImageBytes, response.getBody());
    }

    @Test
    public void testWorksWithGIFFiles() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/image.gif"), "HTTP/1.1");

        Response response = handler.handle(request);

        byte[] expectedImageBytes = Files.readAllBytes(new File(ServerSettings.getRootDirectory(), "/image.gif").toPath());
        assertArrayEquals(expectedImageBytes, response.getBody());
    }
}
