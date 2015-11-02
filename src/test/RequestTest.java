package test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RequestTest {
	@Before
	public void setUp() throws Exception {
		
	}
	@Test
	public void testSetMethodTypeInRequest(){
		Request request = new Request();
		
		request.setMethod("GET");
		
		Assert.assertEquals("GET", request.getMethod());
	}
	
	@Test
	public void testSetRequestPath(){
		Request request = new Request();
		
		request.setPath("/file");
		
		Assert.assertEquals("/file", request.getPath());
	}
	
	@Test
	public void testAddHeader(){
		Request request = new Request();
		
		request.addHeader("User-Agent", "HttpClient");
		
		Assert.assertEquals("HttpClient", request.getHeader("User-Agent"));
	}

	@Test
	public void testAddHttpVersion(){
		Request request = new Request();

		request.setProtocolVersion("HTTP/1.1");

		Assert.assertEquals("HTTP/1.1", request.getProtocolVersion());
	}

	@Test
	public void testBodyDefaultValue(){
		Request request = new Request();
		
		assertNull(request.getBody());
	}
	
	@Test
	public void testSetBodyValue(){
		Request request = new Request();
		
		request.setBody("some=data");
		
		Assert.assertEquals("some=data", request.getBody());
	}
}
