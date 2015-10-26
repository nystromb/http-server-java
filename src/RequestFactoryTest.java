

import static org.junit.Assert.*;

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
		
		assertEquals("GET", req.getRequestMethod());
		assertEquals("/", req.getPath());
		assertEquals("HTTP/1.1", req.getProtocolVersion());
	}
	
	@Test
	public void testIfRequestFactoryReturnsAPostRequest(){
		String request = "POST /form HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		assertEquals("POST", req.getRequestMethod());
		assertEquals("/form", req.getPath());
		assertEquals("HTTP/1.1", req.getProtocolVersion());
	}
	
	@Test
	public void testIfFactoryReturnsAPutRequest(){
		String request = "PUT /form HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		assertEquals("PUT", req.getRequestMethod());
		assertEquals("/form", req.getPath());
		assertEquals("HTTP/1.1", req.getProtocolVersion());
	}
	
	@Test
	public void testIfFactoryReturnsOptionRequest(){
		String request = "OPTIONS /method_options HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		assertEquals("OPTIONS", req.getRequestMethod());
		assertEquals("/method_options", req.getPath());
		assertEquals("HTTP/1.1", req.getProtocolVersion());
	}
}
