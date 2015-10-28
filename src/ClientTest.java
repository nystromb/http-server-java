import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.junit.Before;
import org.junit.Test;

public class ClientTest {
	Client client;
	BufferedReader input;
	PrintWriter output;
	
	ByteArrayOutputStream outputStream;
	
	@Before
	public void setUp() throws Exception {
		client = new Client(new Socket());
		
		outputStream = new ByteArrayOutputStream();
		output = new PrintWriter(outputStream, true);
		client.setOutput(output);
	}

	@Test
	public void testReadsInputWithOneHeader() throws IOException {
		input = new BufferedReader(
					new InputStreamReader(
						new ByteArrayInputStream(("GET / HTTP/1.1\r\nHost: localhost:5000\r\n\r\n").getBytes())));
		
		client.setInput(input);
		
		String request = client.getRequest();
		
		assertEquals("GET / HTTP/1.1\r\nHost: localhost:5000\r\n\r\n", request);
	}

	@Test
	public void testReadsInputWithTwoHeaders() throws IOException {
		input = new BufferedReader(
					new InputStreamReader(
						new ByteArrayInputStream(("GET / HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\n").getBytes())));
		
		client.setInput(input);
		
		String request = client.getRequest();
		
		assertEquals("GET / HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\n", request);
	}
	
	@Test
	public void testReadsInputWithBody() throws IOException {
		input = new BufferedReader(
					new InputStreamReader(
						new ByteArrayInputStream(("GET / HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\nmy=data\r\n").getBytes())));
		
		client.setInput(input);
		
		String request = client.getRequest();
		
		assertEquals("GET / HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\nmy=data\r\n", request);
	}
	
	@Test
	public void testReadsInputWithParameters() throws IOException {
		input = new BufferedReader(
					new InputStreamReader(
						new ByteArrayInputStream(("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\nmy=data\r\n").getBytes())));
		
		client.setInput(input);
		
		String request = client.getRequest();
		
		assertEquals("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1\r\nHost: localhost:5000\r\nUser-Agent: Mozilla/1.1\r\n\r\nmy=data\r\n", request);
	}
	
	@Test
	public void testSendClientOutput() throws IOException {
		client.send("response string");
		
		assertEquals("response string", outputStream.toString());
	}
}
