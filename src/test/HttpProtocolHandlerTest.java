package test;

import main.HttpProtocolHandler;
import main.Main;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.Mocks.MockSocket;

import java.io.*;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 12/1/15.
 */
public class HttpProtocolHandlerTest {
    InputStream input;
    OutputStream output;
    MockSocket client;

    @Before
    public void setUp() throws IOException {
        Main.main(new String[] {"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public"});
        Main.buildRoutes();
        Main.setUpLogger();
        output = new ByteArrayOutputStream();
    }

    @After
    public void shutDown(){

    }
//
//    @Test
//    public void testRootReturns200OK(){
//        input = new ByteArrayInputStream("GET / HTTP/1.1\r\n\r\n".getBytes());
//        client = new MockSocket(input, output);
//        HttpProtocolHandler thread = new HttpProtocolHandler(client);
//
//                thread.run();
//
//
//
//        assertTrue(output.toString().contains("200 OK"));
//    }

}
