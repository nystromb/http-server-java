import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {
	Request req;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testGetRequestLineComponents() {					
		req = new Request("GET", "/", "HTTP/1.1");
		
		assertEquals("GET", req.getMethod());
		assertEquals("/", req.getPath());
		assertEquals("HTTP/1.1", req.getProtocolVersion());
	}
	
	@Test
	public void testHasBodyIsNull(){				
		req = new Request("POST", "/example", "HTTP/1.1");
		
		assertNull(req.body);
	}
	
	@Test
	public void testContainsHeadersInRequest(){
		req = new Request("GET", "/some_page", "HTTP/1.1");
		
		req.headers.put("User-Agent", "HTTPClient/1.1");
		
		assertTrue(req.headers.containsKey("User-Agent"));
	}
}
