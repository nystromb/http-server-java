package test;

import static org.junit.Assert.*;

import main.Request;
import main.RequestHandler;
import main.RequestParser;
import main.Router;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class RequestHandlerTest {
	Request request;
    String response;

	@Before
	public void setUp() throws Exception {
		request = new Request(new String [] {"GET", "/", "HTTP/1.1"});
    }

	@Test
	public void testGetResponseOnRoot() throws IOException{
		assertTrue(RequestHandler.getResponse(request).contains("HTTP/1.1 200 OK"));
	}

    @Test
     public void testPostDataThenGetData() throws IOException{
        request = RequestParser.process("POST /form HTTP/1.1\r\nContent-Length: 9\r\n\r\nsome=data");

        response = RequestHandler.getResponse(request);

        assertTrue(response.contains("HTTP/1.1 200 OK"));

        request = RequestParser.process("GET /form HTTP/1.1\r\n");

        response = RequestHandler.getResponse(request);

        assertTrue(response.contains("some=data"));
    }

    @Test
    public void testPutDataThenGetData() throws IOException{
        request = RequestParser.process("PUT /form HTTP/1.1\r\nContent-Length: 14\r\n\r\ndifferent=data");

        String res = RequestHandler.getResponse(request);

        assertTrue(res.contains("HTTP/1.1 200 OK\r\n"));

        request = RequestParser.process("GET /form HTTP/1.1\r\n\r\n");

        res = RequestHandler.getResponse(request);

        assertTrue(res.contains("different=data"));
    }

    @Test
    public void testDeleteDataThenGetData() throws IOException{
        request = RequestParser.process("PUT /form HTTP/1.1\r\nContent-Length: 14\r\n\r\ndifferent=data");

        response = RequestHandler.getResponse(request);

        assertTrue(response.contains("HTTP/1.1 200 OK\r\n"));

        request = RequestParser.process("GET /form HTTP/1.1\r\n\r\n");

        response = RequestHandler.getResponse(request);

        assertTrue(response.contains("different=data"));

        request = RequestParser.process("DELETE /form HTTP/1.1\r\n\r\n");

        response = RequestHandler.getResponse(request);

        request = RequestParser.process("GET /form HTTP/1.1\r\n\r\n");

        response = RequestHandler.getResponse(request);

        assertFalse(response.contains("different=data"));
    }

	@Test
	public void testOptionsRequestIncludesAllowHeaders() throws IOException{
		request = RequestParser.process("OPTIONS / HTTP/1.1\r\n");

        response = RequestHandler.getResponse(request);

		assertEquals("HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT\r\n\r\n", response);
	}

    @Test
    public void test404NotFoundOnFoobar() throws IOException{
        request = RequestParser.process("GET /foobar HTTP/1.1\r\n\r\n");

        assertTrue(RequestHandler.getResponse(request).contains("HTTP/1.1 404 Not Found"));
    }
}
