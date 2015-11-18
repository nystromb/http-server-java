package main.Handlers;

import main.Request;
import main.ServerReader;
import main.ServerSettings;

import java.io.IOException;
import java.io.File;

/**
 * Created by nystrom on 11/16/15.
 */
public class FileContentReader implements RequestHandler {

    @Override
    public String handle(Request request) throws IOException {
        StringBuffer response = new StringBuffer();

        response.append("HTTP/1.1");
        response.append(" ");
        response.append("200 OK\r\n");
        response.append("\r\n");

        response.append(ServerReader.readFileContents(new File(ServerSettings.getDirectory(), request.getPath())));

        return response.toString();
    }
}
