

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
		
		assertEquals("Get", req.getClass().getName());
		assertEquals("/", req.getPath());
	}
	
	@Test
	public void testIfRequestFactoryReturnsAPostRequest(){
		String request = "POST /form HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		assertEquals("Post", req.getClass().getName());
		assertEquals("/form", req.getPath());
	}
	
	@Test
	public void testIfFactoryReturnsAPutRequest(){
		String request = "PUT /form HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		assertEquals("Put", req.getClass().getName());
		assertEquals("/form", req.getPath());
	}
	
	@Test
	public void testIfFactoryReturnsOptionRequest(){
		String request = "OPTIONS /method_options HTTP/1.1";
		
		Request req = RequestFactory.build(request);
		
		assertEquals("Option", req.getClass().getName());
		assertEquals("/method_options", req.getPath());
	}
}
