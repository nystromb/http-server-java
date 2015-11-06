package test;

import static org.junit.Assert.*;

import main.Request;
import main.ResponseFactory;
import main.Router;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.ws.Response;

public class ResponseFactoryTest {
	Request request;

	@Before
	public void setUp() throws Exception {
		request = new Request();

        Router.addResource("/");
        Router.addResource("/form");
    }

	@Test
	public void testGetResponse() {
		request.setMethod("GET");
		request.setPath("/");
		request.setProtocolVersion("HTTP/1.1");

		assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n", ResponseFactory.getResponse(request));
	}

    @Test
     public void testPostDataThenGetData(){
        request.setMethod("POST");
        request.setPath("/form");
        request.setProtocolVersion("HTTP/1.1");
        request.setBody("some=data");

        String res = ResponseFactory.getResponse(request);

        assertTrue(res.contains("HTTP/1.1 200 OK\r\n"));

        request.setMethod("GET");
        request.setBody("");

        res = ResponseFactory.getResponse(request);

        assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 9\r\n\r\nsome=data", res);
    }

    @Test
    public void testPutDataThenGetData(){
        request.setMethod("PUT");
        request.setPath("/form");
        request.setProtocolVersion("HTTP/1.1");
        request.setBody("different=data");

        String res = ResponseFactory.getResponse(request);

        assertTrue(res.contains("HTTP/1.1 200 OK\r\n"));

        request.setMethod("GET");
        request.setBody("");

        res = ResponseFactory.getResponse(request);

        assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 14\r\n\r\ndifferent=data", res);
    }

    @Test
    public void testDeleteDataThenGetData(){
        request.setMethod("PUT");
        request.setPath("/form");
        request.setProtocolVersion("HTTP/1.1");
        request.setBody("different=data");

        String res = ResponseFactory.getResponse(request);

        assertTrue(res.contains("HTTP/1.1 200 OK\r\n"));

        request.setMethod("GET");
        request.setBody("");

        res = ResponseFactory.getResponse(request);

        assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 14\r\n\r\ndifferent=data", res);

        request.setMethod("DELETE");
        request.setProtocolVersion("HTTP/1.1");

        ResponseFactory.getResponse(request);

        request.setMethod("GET");

        res = ResponseFactory.getResponse(request);

        assertEquals("HTTP/1.1 200 OK\r\nContent-Length: 0\r\n\r\n", res);

    }

	@Test
	public void testOptionsRequestIncludesAllowHeaders() {
		request.setMethod("OPTIONS");
		request.setPath("/");
		request.setProtocolVersion("HTTP/1.1");

		String expectedResponse = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT\r\n\r\n";
		assertEquals(expectedResponse, ResponseFactory.getResponse(request));
	}

    @Test
    public void test404NotFoundOnFoobar(){
        request.setMethod("GET");
        request.setPath("/foobar");
        request.setProtocolVersion("HTTP/1.1");

        assertEquals("HTTP/1.1 404 Not Found\r\nContent-Length: 0\r\n\r\n", ResponseFactory.getResponse(request));
    }
}
