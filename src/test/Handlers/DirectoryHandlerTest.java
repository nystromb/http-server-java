package test.Handlers;

import main.Handlers.DirectoryHandler;
import main.HttpExchange;
import main.Request;
import main.Response;
import main.ServerSettings;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 12/3/15.
 */
public class DirectoryHandlerTest {
    DirectoryHandler handler;

    @Before
    public void setUp(){
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/cob_spec/public/"});
        handler = new DirectoryHandler();
    }

    @Test
    public void testIsARequestHandler(){
        assertTrue(handler instanceof HttpExchange);
    }


    @Test
    public void testReturns200OK() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");

        Response response = handler.exchange(request);

        assertTrue(response.status.contains("200 OK"));
    }

    @Test
    public void testContainsListOfFilesInDirectory() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");

        Response response = handler.exchange(request);

        String expectedResponse = new String(response.toByteArray());
        assertTrue(
                expectedResponse.contains("<li><a href=\"/image.png\">image.png</a></li>") &&
                expectedResponse.contains("<li><a href=\"/image.jpeg\">image.jpeg</a></li>") &&
                expectedResponse.contains("<li><a href=\"/image.gif\">image.gif</a></li>") &&
                expectedResponse.contains("<li><a href=\"/file1\">file1</a></li>") &&
                expectedResponse.contains("<li><a href=\"/file2\">file2</a></li>") &&
                expectedResponse.contains("<li><a href=\"/partial_content.txt\">partial_content.txt</a></li>") &&
                expectedResponse.contains("<li><a href=\"/patch-content.txt\">patch-content.txt</a></li>") &&
                expectedResponse.contains("<li><a href=\"/text-file.txt\">text-file.txt</a></li>")
        );
    }



}
