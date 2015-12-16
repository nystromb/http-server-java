package test.builders;

import http.builders.Request;
import http.builders.RequestParser;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RequestParserTest {
    Request request;

    @Test
    public void testBuildsRequestLine() throws URISyntaxException {
        request = RequestParser.process("GET / HTTP/1.1\r\n\r\n");

        assertEquals("GET", request.getMethod());
        assertEquals("/", request.getPath());
        assertEquals("HTTP/1.1", request.getVersion());
    }

    @Test
    public void testProcessesAHeaderLine() throws URISyntaxException {
        request = RequestParser.process("GET / HTTP/1.1\r\nExample-Header: someValue\r\n\r\n");

        assertTrue(request.hasHeader("Example-Header"));
        assertEquals("someValue", request.getHeader("Example-Header"));
    }

    @Test
    public void testProcessesMultipleHeaders()  throws URISyntaxException{
        request = RequestParser.process("GET / HTTP/1.1\r\nExample-Header: someValue\r\nUser-Agent: HttpClient\r\n\r\n");

        assertTrue(request.hasHeader("Example-Header") && request.hasHeader("User-Agent"));
        assertEquals("someValue", request.getHeader("Example-Header"));
        assertEquals("HttpClient", request.getHeader("User-Agent"));
    }

    @Test
    public void testProcessesBodyDataIfHasContentLengthHeader() throws URISyntaxException{
        request = RequestParser.process("GET / HTTP/1.1\r\nExample-Header: someValue\r\nUser-Agent: HttpClient\r\nContent-Length: 9\r\n\r\nsome=data");

        assertEquals("some=data", request.getBody());
    }

    @Test
    public void testWithHeaderWith3Components() throws URISyntaxException{
        request = RequestParser.process("GET /logs HTTP/1.1\r\nExample-Header: someValue\r\nUser-Agent: HttpClient\r\nAuthorization: Basic AKSFDLSDFlskdfj\r\nsome=data");

        assertEquals("/logs", request.getPath());
    }
}
