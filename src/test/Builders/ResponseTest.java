package test.Builders;

import http.Builders.Response;
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
        Response response = new Response.Builder(200).build();

        assertEquals("HTTP/1.1 200 OK\r\n\r\n", new String(response.toByteArray()));
    }

    @Test
    public void testAddHeadersToResponse() throws IOException {
        Response response = new Response.Builder(200, "some=data").build();

        assertEquals("HTTP/1.1 200 OK", response.statusLine);
        assertEquals("9", response.headers.get("Content-Length"));
    }

    @Test
    public void testSettingTheBody() throws IOException {
        Response response = new Response.Builder(200, "some=data").build();

        assertTrue(new String(response.toByteArray()).contains("Content-Length: 9\r\n"));
        assertTrue(new String(response.toByteArray()).contains("some=data"));
        assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 9\r\n\r\nsome=data", new String(response.toByteArray()));
    }
}
