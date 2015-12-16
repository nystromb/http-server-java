package test.server;

import http.request.Request;
import http.configuration.RouterConfig;
import http.server.ServerRunner;
import org.junit.Before;
import org.junit.Test;
import test.mocks.MockSocket;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ServerRunnerTest {
    InputStream input;
    OutputStream output;
    MockSocket client;

    @Before
    public void setUp() throws IOException {
        RouterConfig.setUpRoutes();
        output = new ByteArrayOutputStream();
        input = new ByteArrayInputStream("".getBytes());
        client = new MockSocket(input, output);
    }

    @Test
    public void testRootReturns200OK() throws URISyntaxException {
        Request request = new Request("GET", new URI("/"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testLogsReturns401Unauthorized() throws URISyntaxException {
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("401 Unauthorized"));
    }

    @Test
    public void testLogsReturns200IfHeadersAreSent() throws URISyntaxException {
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");
        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testFormRoute() throws URISyntaxException {
        Request request = new Request("GET", new URI("/form"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testMethodOptions() throws URISyntaxException {
        Request request = new Request("GET", new URI("/method_options"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testParameters() throws URISyntaxException {
        Request request = new Request("GET", new URI("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testFile1GetsContents() throws URISyntaxException {
        Request request = new Request("GET", new URI("/file1"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }
    @Test
    public void testGetsPNGContents() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.png"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testGetsJPEGContents() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.jpeg"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testGetsGIFContents() throws URISyntaxException {
        Request request = new Request("GET", new URI("/image.gif"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
         public void testGetsTextFileContents() throws URISyntaxException {
        Request request = new Request("GET", new URI("/text-file.txt"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testGetsPartialContents() throws URISyntaxException {
        Request request = new Request("GET", new URI("/partial_content.txt"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testGetsPatchContentFile() throws URISyntaxException {
        Request request = new Request("GET", new URI("/patch-content.txt"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testGetsRedirect302() throws URISyntaxException {
        Request request = new Request("GET", new URI("/redirect"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("302 Found"));
    }

    @Test
    public void testGetsFile2OK() throws URISyntaxException {
        Request request = new Request("GET", new URI("/file2"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testPostsFile2NotAllowed() throws URISyntaxException {
        Request request = new Request("POST", new URI("/file1"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertEquals("405 Method Not Allowed",output.toString());
    }

    @Test
    public void testFirstChecksFor404ForUndefinedRoute() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/foobar"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("404 Not Found"));
    }

    @Test
    public void testForSuccess() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/tictactoe"), "HTTP/1.1");

        ServerRunner thread = new ServerRunner(client, request);
        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }
}
