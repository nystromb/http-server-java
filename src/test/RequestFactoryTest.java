package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestFactoryTest {
	String request = "";
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testIfRequestFactoryReturnsAGetRequest() {
		String request = "GET / HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		Assert.assertEquals("GET", req.getMethod());
		Assert.assertEquals("/", req.getPath());
		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
	}

	@Test
	public void testIfRequestFactoryReturnsAPostRequest(){
		String request = "POST /form HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		Assert.assertEquals("POST", req.getMethod());
		Assert.assertEquals("/form", req.getPath());
		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
	}

	@Test
	public void testIfFactoryReturnsAPutRequest(){
		String request = "PUT /form HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		Assert.assertEquals("PUT", req.getMethod());
		Assert.assertEquals("/form", req.getPath());
		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
	}

	@Test
	public void testIfFactoryReturnsOptionRequest(){
		String request = "OPTIONS /method_options HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		Assert.assertEquals("OPTIONS", req.getMethod());
		Assert.assertEquals("/method_options", req.getPath());
		Assert.assertEquals("HTTP/1.1", req.getProtocolVersion());
	}

	@Test
	public void testIfAddsOneHeader(){
		String request = "OPTIONS /path HTTP/1.1\r\nUser-Agent: HTTPClient/1.1\r\n\r\n";
		
		Request req = RequestFactory.build(request);
		
		Assert.assertEquals("HTTPClient/1.1", req.headers.get("User-Agent"));
		assertTrue(req.headers.containsKey("User-Agent"));
	}

	@Test
	public void testIfAddsMultipleHeaders(){
		String request = "GET /path HTTP/1.1\r\nUser-Agent: HTTPClient/1.1\r\nContent-Length: 234\r\n\r\n";
		
		Request req = RequestFactory.build(request);
		
		Assert.assertEquals("HTTPClient/1.1", req.headers.get("User-Agent"));
		assertTrue(req.headers.containsKey("User-Agent"));
		
		Assert.assertEquals("234", req.headers.get("Content-Length"));
		assertTrue(req.headers.containsKey("Content-Length"));
	}

	@Test
	public void testIfAddsBodyToRequest(){
		String request = "GET /path HTTP/1.1\r\nUser-Agent: HTTPClient/1.1\r\nContent-Length: 234\r\n\r\nSomebody=Data";
		
		Request req = RequestFactory.build(request);
		
		Assert.assertEquals("Somebody=Data", req.body);
	}
}
