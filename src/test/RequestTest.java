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

    @Test
    public void testQueryDecode() throws URISyntaxException{
        Request request = new Request("GET", new URI("/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff"), "HTTP/1.1");

        request.setBody("some=data");

        Assert.assertTrue(request.getQuery().contains("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?"));
        Assert.assertTrue(request.getQuery().contains("variable_2 = stuff"));
    }
}
