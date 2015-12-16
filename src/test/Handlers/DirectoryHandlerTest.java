package test.handlers;

import http.request.Request;
import http.response.Response;
import http.handlers.DirectoryHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;

public class DirectoryHandlerTest {
    DirectoryHandler handler = new DirectoryHandler();

    @Before
    public void setUp(){

    }

    @Test
    public void testReturns200OK() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("200 OK"));
    }

    @Test
    public void testContainsListOfFilesInDirectory() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");

        Response response = handler.handle(request);

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
