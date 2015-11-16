package main;

import java.io.File;
import java.io.IOException;

/**
 * Created by nystrom on 11/16/15.
 */
public class DirectoryReader implements RequestHandler {
    @Override
    public String handle(Request request) throws IOException {
        StringBuffer response = new StringBuffer();
        response.append("HTTP/1.1 200 OK\r\n");
        response.append("\r\n");
        response.append(ServerReader.readDirectoryContents(new File(ServerSettings.getDirectory(), request.getPath())));
        return response.toString();
    }
}
