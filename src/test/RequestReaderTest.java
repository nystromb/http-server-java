package test;

import main.Request;
import main.RequestReader;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 11/11/15.
 */
public class RequestReaderTest {

    @Test
    public void testReadsStatusLine() throws IOException {
        BufferedReader input = new BufferedReader(
                        new InputStreamReader(
                                new ByteArrayInputStream("GET / HTTP/1.1\r\n\r\n".getBytes())));

        String rawRequest = RequestReader.read(input);

        assertEquals("GET / HTTP/1.1\r\n\r\n", rawRequest);
    }

    @Test
    public void testReadsInputWithHeaders() throws IOException {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream("GET / HTTP/1.1\r\nUser-Agent: HttpClient/1.1\r\n\r\n".getBytes())));

        String rawRequest = RequestReader.read(input);

        assertEquals("GET / HTTP/1.1\r\nUser-Agent: HttpClient/1.1\r\n\r\n", rawRequest);
    }

    @Test
    public void testReadsARequestWithABodyGivenTheContentLength() throws IOException {
        BufferedReader input = new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream("GET / HTTP/1.1\r\nUser-Agent: HttpClient/1.1\r\nContent-Length: 9\r\n\r\nsome=data".getBytes())));

        String rawRequest = RequestReader.read(input);

        assertEquals("GET / HTTP/1.1\r\nUser-Agent: HttpClient/1.1\r\nContent-Length: 9\r\n\r\nsome=data", rawRequest);
    }
}
