package test;

import static org.junit.Assert.*;

import main.Request;
import main.RequestParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestParserTest {

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testIfRequestFactoryReturnsAGetRequest() {
		String request = "GET / HTTP/1.1";
		
		Request req = RequestParser.build(request);
		
		Assert.assertEquals("GET", req.getMethod());
		Assert.assertEquals("/", req.getPath());
		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
	}

	@Test
	public void testIfRequestFactoryReturnsAPostRequest(){
		String request = "POST /form HTTP/1.1";
		
		Request req = RequestParser.build(request);
		
		Assert.assertEquals("POST", req.getMethod());
		Assert.assertEquals("/form", req.getPath());
		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
	}

	@Test
	public void testIfFactoryReturnsAPutRequest(){
		String request = "PUT /form HTTP/1.1";
		
		Request req = RequestParser.build(request);
		
		Assert.assertEquals("PUT", req.getMethod());
		Assert.assertEquals("/form", req.getPath());
		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
	}

	@Test
	public void testIfFactoryReturnsOptionRequest(){
		String request = "OPTIONS /method_options HTTP/1.1";
		
		Request req = RequestParser.build(request);
		
		Assert.assertEquals("OPTIONS", req.getMethod());
		Assert.assertEquals("/method_options", req.getPath());
		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
	}

	@Test
	public void testIfAddsOneHeader(){
		String request = "OPTIONS /path HTTP/1.1\r\nUser-Agent: HTTPClient/1.1\r\n\r\n";
		
		Request req = RequestParser.build(request);
		
		Assert.assertEquals("HTTPClient/1.1", req.getHeader("User-Agent"));
		assertTrue(req.hasHeader("User-Agent"));
	}

	@Test
	public void testIfAddsMultipleHeaders(){
		String request = "GET /path HTTP/1.1\r\nUser-Agent: HTTPClient/1.1\r\nContent-Length: 0\r\n\r\n";
		
		Request req = RequestParser.build(request);
		
		Assert.assertEquals("HTTPClient/1.1", req.getHeader("User-Agent"));
		assertTrue(req.hasHeader("User-Agent"));
		
		Assert.assertEquals("0", req.getHeader("Content-Length"));
		assertTrue(req.hasHeader("Content-Length"));
	}

	@Test
	public void testIfAddsBodyToRequest(){
		String request = "GET /path HTTP/1.1\r\nUser-Agent: HTTPClient/1.1\r\nContent-Length: 13\r\n\r\nSomebody=Data";
		
		Request req = RequestParser.build(request);
		
		Assert.assertEquals("Somebody=Data", req.getBody());
	}
}