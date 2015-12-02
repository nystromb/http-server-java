package test;

import main.HttpProtocolHandler;
import main.Handlers.DirectoryReader;
import main.Router;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.Mocks.MockSocket;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 12/1/15.
 */
public class HttpProtocolHandlerTest {
    InputStream input;
    OutputStream output;
    MockSocket client;

    @Before
    public void setUp(){
//        Router.addRoute("/", new DirectoryReader());

        output = new ByteArrayOutputStream();
    }

    @After
    public void shutDown(){

    }

    @Test
    public void testRootReturns200OK(){
        input = new ByteArrayInputStream("GET / HTTP/1.1\r\n\r\n".getBytes());
        client = new MockSocket(input, output);
        HttpProtocolHandler thread = new HttpProtocolHandler(client);

        thread.run();

        assertTrue(output.toString().contains("200 OK"));
    }

    @Test
    public void testRootHasNoPostMethod(){
        input = new ByteArrayInputStream("POST / HTTP/1.1\r\n\r\n".getBytes());
        client = new MockSocket(input, output);
        HttpProtocolHandler thread = new HttpProtocolHandler(client);

        thread.run();

        assertTrue(output.toString().contains("405 Method Not Allowed"));
    }

    public void testFormReturns200OK(){
        input = new ByteArrayInputStream("GET /form HTTP/1.1\r\n\r\n".getBytes());
        client = new MockSocket(input, output);
        HttpProtocolHandler thread = new HttpProtocolHandler(client);

        thread.run();

        assertEquals(" ", output.toString());

        input = new ByteArrayInputStream("POST /form HTTP/1.1".getBytes());
        thread.run();
        assertEquals("HTTP/1.1 200 OK\r\n", output.toString());

    }
}
