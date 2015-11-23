package main.Handlers;

import main.Request;
import main.ServerReader;
import main.ServerSettings;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by nystrom on 11/17/15.
 */
public class ImageFileReader implements RequestHandler {


    @Override
    public byte[] handle(Request request) throws IOException {
        ByteArrayOutputStream response = new ByteArrayOutputStream();

        response.write("HTTP/1.1 200 OK\r\n".getBytes());

        response.write("\r\n".getBytes());

        response.write(ServerReader.readImageContents(new File(ServerSettings.getDirectory(), request.getPath())));

        return response.toByteArray();
    }
}
