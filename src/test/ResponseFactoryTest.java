package test;

import static org.junit.Assert.*;

import main.Request;
import main.ResponseFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ResponseFactoryTest {
	Request request;

	@Before
	public void setUp() throws Exception {
		request = new Request();
	}

	@Test
	public void testGetResponse() {
		request.setMethod("GET");
		request.setPath("/");
		request.setProtocolVersion("HTTP/1.1");

		assertEquals("HTTP/1.1 200 OK\r\n\r\n", ResponseFactory.getResponse(request));
	}

	@Test
	public void testIncludesAllowHeaderOnOptionsRequest() {
		request.setMethod("OPTIONS");
		request.setPath("/");
		request.setProtocolVersion("HTTP/1.1");

		String expectedResponse = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT\r\n\r\n";
		assertEquals(expectedResponse, ResponseFactory.getResponse(request));
	}

	@Test
	public void testPutsDataInBody(){
		request.setMethod("POST");
		request.setPath("/form");
		request.setProtocolVersion("HTTP/1.1");
		request.setBody("some=data");

		assertEquals("HTTP/1.1 200 OK\r\n\r\nsome=data", ResponseFactory.getResponse(request));
	}
}
