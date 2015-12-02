package test.Mocks;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by nystrom on 12/1/15.
 */
public class MockSocket extends Socket {
    InputStream input;
    OutputStream output;

    public MockSocket(InputStream input, OutputStream output){
        this.input = input;
        this.output = output;
    }

    @Override
    public InputStream getInputStream(){
        return input;
    }

    @Override
    public OutputStream getOutputStream(){
        return output;
    }
}
