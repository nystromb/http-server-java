package test.handlers;

import http.request.Request;
import http.response.Response;
import http.handlers.FileHandler;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.*;

public class FileHandlerTest {
    FileHandler handler = new FileHandler();

    @Test
    public void testReturns200OK() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/file1"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("200 OK"));
    }

    @Test
    public void testReturnsFile1Contents() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/file1"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(new String(response.toByteArray()).contains("file1 contents"));
    }

    @Test
    public void testMethodNotAllowedForPostRequest() throws IOException, URISyntaxException {
        Request request = new Request("POST", new URI("/file1"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("405"));
    }

    @Test
    public void testMethodNotAllowedForPutRequest() throws IOException, URISyntaxException {
        Request request = new Request("PUT", new URI("/file1"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("405"));
    }

    @Test
    public void testRangeHeaderReturns206Code() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/partial_content.txt"), "HTTP/1.1");
        request.addHeader("Range", "bytes=0-4");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("206"));
    }

    @Test
    public void testRangeHeaderReturnsPartialFileContentsRange04() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/partial_content.txt"), "HTTP/1.1");
        request.addHeader("Range", "bytes=0-4");

        Response response = handler.handle(request);

        assertArrayEquals("This ".getBytes(), response.body);
    }

    @Test
    public void testRangeHeaderReturnsPartialFileContentsStart4() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/partial_content.txt"), "HTTP/1.1");
        request.addHeader("Range", "bytes=4-");

        Response response = handler.handle(request);

        assertTrue(new String(response.toByteArray()).endsWith(" is a file that contains text to read part of in order to fulfill a 206.\n"));
    }


    @Test
    public void testRangeHeaderReturnsPartialFileContentsEnd6() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/partial_content.txt"), "HTTP/1.1");
        request.addHeader("Range", "bytes=-6");

        Response response = handler.handle(request);

        assertTrue(new String(response.toByteArray()).endsWith(" 206.\n"));
    }

    @Test
    public void testPatchContent() throws URISyntaxException, IOException {
        Request request = new Request("PATCH", new URI("/patch-content.txt"), "HTTP/1.1");
        request.setBody("patched content");
        request.addHeader("ETag", "dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec");

        Response response = handler.handle(request);

        assertTrue(response.statusLine.contains("204"));
        assertFalse(new String(response.toByteArray()).contains("patched content"));

        request = new Request("GET", new URI("/patch-content.txt"), "HTTP/1.1");

        response = handler.handle(request);

        assertTrue(new String(response.toByteArray()).contains("patched content"));
    }
}
