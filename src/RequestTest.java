import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RequestTest {
	Request req;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void test() {
		req = new Request("GET", "/", "HTTP/1.1");
		
		assertEquals("GET", req.getRequestMethod());
		assertEquals("/", req.getPath());
		assertEquals("HTTP/1.1", req.getProtocolVersion());
	}

}
