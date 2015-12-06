package test.Handlers;

import main.Handlers.FileHandler;
import main.Handlers.Requestable;
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
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/cob_spec/public/"});
        handler = new FileHandler();
    }

    @Test
    public void testIsARequestHandler(){
        assertTrue(handler instanceof Requestable);
    }

    @Test
    public void testReturns200OK() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/file1"), "HTTP/1.1");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).contains("200 OK"));
    }

    @Test
    public void testReturnsFile1Contents() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/file1"), "HTTP/1.1");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).contains("file1 contents"));
    }

    @Test
    public void testMethodNotAllowedForPostRequest() throws IOException, URISyntaxException {
        Request request = new Request("POST", new URI("/file1"), "HTTP/1.1");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).contains("405"));
    }

    @Test
    public void testMethodNotAllowedForPutRequest() throws IOException, URISyntaxException {
        Request request = new Request("PUT", new URI("/file1"), "HTTP/1.1");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).contains("405"));
    }

    @Test
    public void testRangeHeaderReturns206Code() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/partial_content.txt"), "HTTP/1.1");
        request.addHeader("Range", "bytes=0-4");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).contains("206 Partial Content"));
    }

    @Test
    public void testRangeHeaderReturnsPartialFileContentsRange04() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/partial_content.txt"), "HTTP/1.1");
        request.addHeader("Range", "bytes=0-4");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).endsWith("This "));
    }

    @Test
    public void testRangeHeaderReturnsPartialFileContentsStart4() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/partial_content.txt"), "HTTP/1.1");
        request.addHeader("Range", "bytes=4-");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).endsWith(" is a file that contains text to read part of in order to fulfill a 206.\n"));
    }


    @Test
    public void testRangeHeaderReturnsPartialFileContentsEnd6() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/partial_content.txt"), "HTTP/1.1");
        request.addHeader("Range", "bytes=-6");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).endsWith(" 206.\n"));
    }

    @Test
    public void testPatchContent() throws URISyntaxException, IOException {
        Request request = new Request("PATCH", new URI("/patch-content.txt"), "HTTP/1.1");
        request.setBody("patched content");
        request.addHeader("ETag", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");

        byte[] response = handler.getResponse(request);

        assertTrue(new String(response).contains("204"));
        assertFalse(new String(response).contains("patched content"));

        request = new Request("GET", new URI("/patch-content.txt"), "HTTP/1.1");

        response = handler.getResponse(request);

        assertTrue(new String(response).contains("patched content"));
    }
}
