package test;

import static org.junit.Assert.*;

import main.Request;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestTest {
	@Before
	public void setUp() throws Exception {
		
	}
	@Test
	public void testSetMethodTypeInRequest(){
		Request request = new Request(new String[] {"GET", "/", "HTTP/1.1"});
		
		assertEquals("GET", request.getMethod());
        assertEquals("/", request.getPath());
        assertEquals("HTTP/1.1", request.getVersion());
	}

	@Test
	public void testAddHeader(){
		Request request = new Request(new String[] {"POST", "/file", "HTTP/1.1"});
		
		request.addHeader("User-Agent", "HttpClient");
		
		assertEquals("HttpClient", request.getHeader("User-Agent"));
	}

	@Test
	public void testBodyDefaultValue(){
		Request request = new Request();
		
		assertTrue(request.getBody().isEmpty());
	}
	
	@Test
	public void testSetBodyValue(){
		Request request = new Request(new String[]{ "GET", "/some/path", "HTTP/1.1"});
		
		request.setBody("some=data");
		
		Assert.assertEquals("some=data", request.getBody());
	}
}
