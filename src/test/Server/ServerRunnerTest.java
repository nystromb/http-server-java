package test.Server;

import http.Configuration.Settings;
import org.junit.After;
import org.junit.Before;
import test.Mocks.MockSocket;

import java.io.*;

/**
 * Created by nystrom on 12/1/15.
 */
public class ServerRunnerTest {
    InputStream input;
    OutputStream output;
    MockSocket client;

    @Before
    public void setUp() throws IOException {
        Settings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public"});
        output = new ByteArrayOutputStream();
    }

    @After
    public void shutDown(){

    }

//    @Test
//    public void testRootReturns200OK(){
//        input = new ByteArrayInputStream("GET / HTTP/1.1\r\n\r\n".getBytes());
//        client = new MockSocket(input, output);
//        ServerRunner thread = new ServerRunner(client, new Routes());
//
//        thread.run();
//
//        assertTrue(output.toString().contains("200 OK"));
//    }

}
