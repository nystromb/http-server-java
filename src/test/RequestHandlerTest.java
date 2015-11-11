package test;

import static org.junit.Assert.*;

import main.Request;
import main.RequestHandler;
import main.Router;
import org.junit.Before;
import org.junit.Test;

public class RequestHandlerTest {
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

		assertTrue(RequestHandler.getResponse(request).contains("HTTP/1.1 200 OK"));
	}

    @Test
     public void testPostDataThenGetData(){
        request.setMethod("POST");
        request.setPath("/form");
        request.setProtocolVersion("HTTP/1.1");
        request.setBody("some=data");

        String res = RequestHandler.getResponse(request);

        assertTrue(res.contains("HTTP/1.1 200 OK\r\n"));

        request.setMethod("GET");
        request.setBody("");

        res = RequestHandler.getResponse(request);

        assertTrue(res.contains("some=data"));
    }

    @Test
    public void testPutDataThenGetData(){
        request.setMethod("PUT");
        request.setPath("/form");
        request.setProtocolVersion("HTTP/1.1");
        request.setBody("different=data");

        String res = RequestHandler.getResponse(request);

        assertTrue(res.contains("HTTP/1.1 200 OK\r\n"));

        request.setMethod("GET");
        request.setBody("");

        res = RequestHandler.getResponse(request);

        assertTrue(res.contains("different=data"));
    }

    @Test
    public void testDeleteDataThenGetData(){
        request.setMethod("PUT");
        request.setPath("/form");
        request.setProtocolVersion("HTTP/1.1");
        request.setBody("different=data");

        String res = RequestHandler.getResponse(request);

        assertTrue(res.contains("HTTP/1.1 200 OK\r\n"));

        request.setMethod("GET");
        request.setBody("");

        res = RequestHandler.getResponse(request);

        assertTrue(res.contains("different=data"));

        request.setMethod("DELETE");
        request.setProtocolVersion("HTTP/1.1");

        RequestHandler.getResponse(request);

        request.setMethod("GET");

        res = RequestHandler.getResponse(request);

        assertFalse(res.contains("different=data"));

    }

	@Test
	public void testOptionsRequestIncludesAllowHeaders() {
		request.setMethod("OPTIONS");
		request.setPath("/");
		request.setProtocolVersion("HTTP/1.1");

		String expectedResponse = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT\r\n\r\n";
		assertEquals(expectedResponse, RequestHandler.getResponse(request));
	}

    @Test
    public void test404NotFoundOnFoobar(){
        request.setMethod("GET");
        request.setPath("/foobar");
        request.setProtocolVersion("HTTP/1.1");

        assertTrue(RequestHandler.getResponse(request).contains("HTTP/1.1 404 Not Found"));
    }
}
