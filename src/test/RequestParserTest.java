package test;

import static org.junit.Assert.*;

import main.Request;
import main.RequestParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestParserTest {
    Request request;

    @Test
    public void testBuildsRequestLine(){
        request = RequestParser.process("GET / HTTP/1.1\r\n\r\n");

        assertEquals("GET", request.getMethod());
        assertEquals("/", request.getPath());
        assertEquals("HTTP/1.1", request.getVersion());
    }

    @Test
    public void testProcessesAHeaderLine(){
        request = RequestParser.process("GET / HTTP/1.1\r\nExample-Header: someValue\r\n\r\n");

        assertTrue(request.hasHeader("Example-Header"));
        assertEquals("someValue", request.getHeader("Example-Header"));
    }

    @Test
    public void testProcessesMultipleHeaders(){
        request = RequestParser.process("GET / HTTP/1.1\r\nExample-Header: someValue\r\nUser-Agent: HttpClient\r\n\r\n");

        assertTrue(request.hasHeader("Example-Header") && request.hasHeader("User-Agent"));
        assertEquals("someValue", request.getHeader("Example-Header"));
        assertEquals("HttpClient", request.getHeader("User-Agent"));
    }

    @Test
    public void testProcessesBodyDataIfHasContentLengthHeader(){
        request = RequestParser.process("GET / HTTP/1.1\r\nExample-Header: someValue\r\nUser-Agent: HttpClient\r\nContent-Length: 9\r\n\r\nsome=data");

        assertEquals("some=data", request.getBody());
    }

    @Test
    public void testDoesNotProcessBodyDataIfHasContentLengthHeader(){
        request = RequestParser.process("GET / HTTP/1.1\r\nExample-Header: someValue\r\nUser-Agent: HttpClient\r\n\r\nsome=data");

        assertEquals("", request.getBody());
    }
//	@Test
//	public void testIfRequestFactoryReturnsAGetRequest() {
//		String request = "GET / HTTP/1.1";
//
//		Request req = RequestParser.build(request);
//
//		Assert.assertEquals("GET", req.getMethod());
//		Assert.assertEquals("/", req.getPath());
//		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
//	}
//
//	@Test
//	public void testIfRequestFactoryReturnsAPostRequest(){
//		String request = "POST /form HTTP/1.1";
//
//		Request req = RequestParser.build(request);
//
//		Assert.assertEquals("POST", req.getMethod());
//		Assert.assertEquals("/form", req.getPath());
//		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
//	}
//
//	@Test
//	public void testIfFactoryReturnsAPutRequest(){
//		String request = "PUT /form HTTP/1.1";
//
//		Request req = RequestParser.build(request);
//
//		Assert.assertEquals("PUT", req.getMethod());
//		Assert.assertEquals("/form", req.getPath());
//		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
//	}
//
//	@Test
//	public void testIfFactoryReturnsOptionRequest(){
//		String request = "OPTIONS /method_options HTTP/1.1";
//
//		Request req = RequestParser.build(request);
//
//		Assert.assertEquals("OPTIONS", req.getMethod());
//		Assert.assertEquals("/method_options", req.getPath());
//		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
//	}
//
//	@Test
//	public void testIfAddsOneHeader(){
//		String request = "OPTIONS /path HTTP/1.1\r\nUser-Agent: HTTPClient/1.1\r\n\r\n";
//
//		Request req = RequestParser.build(request);
//
//		Assert.assertEquals("HTTPClient/1.1", req.getHeader("User-Agent"));
//		assertTrue(req.hasHeader("User-Agent"));
//	}
//
//	@Test
//	public void testIfAddsMultipleHeaders(){
//		String request = "GET /path HTTP/1.1\r\nUser-Agent: HTTPClient/1.1\r\nContent-Length: 0\r\n\r\n";
//
//		Request req = RequestParser.build(request);
//
//		Assert.assertEquals("HTTPClient/1.1", req.getHeader("User-Agent"));
//		assertTrue(req.hasHeader("User-Agent"));
//
//		Assert.assertEquals("0", req.getHeader("Content-Length"));
//		assertTrue(req.hasHeader("Content-Length"));
//	}
//
//	@Test
//	public void testIfAddsBodyToRequest(){
//		String request = "GET /path HTTP/1.1\r\nUser-Agent: HTTPClient/1.1\r\nContent-Length: 13\r\n\r\nSomebody=Data";
//
//		Request req = RequestParser.build(request);
//
//		Assert.assertEquals("Somebody=Data", req.getBody());
//	}
}
