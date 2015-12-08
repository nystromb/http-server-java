package test.Server;

import main.Server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Executors;
import static org.junit.Assert.*;

/**
 * Created by nystrom on 12/1/15.
 */
public class HttpServerTest {
    private final int port = 5000;
    HttpServer server;

    @Before
    public void setUp() throws IOException{
        server = new HttpServer(port);
        server.setExecutor(Executors.newFixedThreadPool(15));
    }

    @After
    public void shutDownServer() throws IOException {
        server.close();

        assertTrue(server.isClosed());
    }

    @Test
    public void testStartServerWithPort() throws IOException {
        assertFalse(server.isClosed());
    }

    @Test
    public void testSetExecutorThreadService() throws IOException {
        assertFalse(server.getExecutor().isShutdown());
    }

}


