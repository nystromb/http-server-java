package test;

import static org.junit.Assert.*;

import main.*;
import main.Handlers.RequestHandler;
import main.Handlers.Route;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class RouteTest {

    @Before
    public void setUp() throws Exception{
        Router.addRoute("/form", new Route());
    }

    @Test
    public void testGetFormController() throws IOException{
        Request request = RequestParser.process("GET /form HTTP/1.1\r\n");

        RequestHandler handler = Router.route(request);

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(), handler.handle(request));
    }

    @Test
    public void testPostThenGetFormController() throws IOException{
        //POST /form
        Request request = RequestParser.process("POST /form HTTP/1.1\r\nContent-Length: 9\r\n\r\nsome=data");

        RequestHandler handler = Router.route(request);

        handler.handle(request);

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(), handler.handle(request));

        //GET /form
        request = RequestParser.process("GET /form HTTP/1.1\r\n\r\n");

        handler = Router.route(request);

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\nsome=data".getBytes(), handler.handle(request));
    }

    @Test
    public void testPutThenGetFormController() throws IOException{
        //PUT /form
        Request request = RequestParser.process("PUT /form HTTP/1.1\r\nContent-Length: 9\r\n\r\nsome=data");

        RequestHandler handler = Router.route(request);

        handler.handle(request);

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(), handler.handle(request));

        //GET /form
        request = RequestParser.process("GET /form HTTP/1.1\r\n\r\n");

        handler = Router.route(request);

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\nsome=data".getBytes(), handler.handle(request));
    }

    @Test
    public void testDeleteThenGetFormController() throws IOException{
        //DELETE /form
        Request request = RequestParser.process("DELETE /form HTTP/1.1\r\n\r\n");

        RequestHandler handler = Router.route(request);

        handler.handle(request);

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(), handler.handle(request));

        //GET /form
        request = RequestParser.process("GET /form HTTP/1.1\r\n\r\n");

        handler = Router.route(request);

        assertArrayEquals("HTTP/1.1 200 OK\r\n\r\n".getBytes(),handler.handle(request));
    }

}
