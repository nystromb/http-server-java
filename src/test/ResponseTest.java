package test;

import main.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 12/2/15.
 */
public class ResponseTest {
    @Before
    public void setUp(){

    }

    @Test
    public void testBuildsResponseFromStatusLine() throws IOException{
        Response response = new Response();

        response.setStatus("200 OK");

        assertEquals("HTTP/1.1 200 OK\r\n\r\n", new String(response.toByteArray()));
    }

    @Test
    public void testAddHeadersToResponse() throws IOException {
        Response response = new Response();

        response.addHeader("Content-Length", "9");

        assertEquals("9", response.getHeader("Content-Length"));
    }

    @Test
    public void testSettingTheBody() throws IOException {
        Response response = new Response();

        response.setStatus("200 OK");
        response.setBody("some=data".getBytes());

        assertTrue(new String(response.toByteArray()).contains("Content-Length: 9\r\n"));
        assertTrue(new String(response.toByteArray()).contains("some=data"));
        assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 9\r\n\r\nsome=data", new String(response.toByteArray()));
    }
}
