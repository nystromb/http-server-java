package test;

import static org.junit.Assert.*;

import main.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

public class RequestTest {
	@Before
	public void setUp() throws Exception {
		
	}
	@Test
	public void testSetMethodTypeInRequest() throws URISyntaxException {
		Request request = new Request("GET", new URI("/?some=query"), "HTTP/1.1");
		
		assertEquals("GET", request.getMethod());
        assertEquals("/", request.getPath());
        assertEquals("some=query", request.getQuery());
        assertEquals("HTTP/1.1", request.getVersion());
	}

	@Test
	public void testAddHeader() throws URISyntaxException {
		Request request = new Request("POST", new URI("/file"), "HTTP/1.1");
		
		request.addHeader("User-Agent", "HttpClient");
		
		assertEquals("HttpClient", request.getHeader("User-Agent"));
	}

	@Test
	public void testBodyDefaultValue() throws URISyntaxException{
		Request request = new Request("GET", new URI("/some/path/to/file"), "HTTP/1.1");
		
		assertTrue(request.getBody().isEmpty());
	}
	
	@Test
	public void testSetBodyValue() throws URISyntaxException{
		Request request = new Request("GET", new URI("/some/path"), "HTTP/1.1");
		
		request.setBody("some=data");
		
		Assert.assertEquals("some=data", request.getBody());
	}
}
