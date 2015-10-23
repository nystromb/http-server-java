

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Before;
import org.junit.Test;

public class RequestReaderTest {
	BufferedReader input;
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testReadsInputWithOneHeader() throws IOException {
		input = new BufferedReader(
					new InputStreamReader(
						new ByteArrayInputStream(("GET / HTTP/1.1\r\nHost: localhost:5000\r\n\r\n").getBytes())));
		
		String request = RequestReader.read(input);
		
		assertEquals("GET / HTTP/1.1\r\nHost: localhost:5000\r\n\r\n", request);
	}

	@Test
	public void testReadsInputWithTwoHeaders() throws IOException {
		input = new BufferedReader(
					new InputStreamReader(
						new ByteArrayInputStream(("GET / HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\n").getBytes())));
		
		String request = RequestReader.read(input);
		
		assertEquals("GET / HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\n", request);
	}
	
	@Test
	public void testReadsInputWithBody() throws IOException {
		input = new BufferedReader(
					new InputStreamReader(
						new ByteArrayInputStream(("GET / HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\nmy=data\n").getBytes())));
		
		String request = RequestReader.read(input);
		
		assertEquals("GET / HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\nmy=data\n", request);
	}

}
