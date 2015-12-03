package test;

import main.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
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

        assertArrayEquals("HTTP/1.1 200 OK\r\n".getBytes(), response.toByteArray());
    }

    @Test
    public void testAddHeadersToResponse() throws IOException {
        Response response = new Response();

        response.setHeader("Content-Length", "9");

        assertEquals("9", response.getHeader("Content-Length"));
    }

    @Test
    public void testSettingTheBody() throws IOException {
        Response response = new Response();

        response.setBody("some=data".getBytes());

        assertTrue(new String(response.toByteArray()).contains("Content-Length: 9\r\n"));
        assertTrue(new String(response.toByteArray()).contains("some=data"));
    }
}
