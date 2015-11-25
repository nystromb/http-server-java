package main.Handlers;

import main.Request;
import main.ServerReader;
import main.ServerSettings;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

/**
 * Created by nystrom on 11/16/15.
 */
public class FileContentReader implements RequestHandler {

    @Override
    public byte[] handle(Request request) throws IOException {
        StringBuffer response = new StringBuffer();

        response.append("HTTP/1.1");
        response.append(" ");
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            response.append("405 Method Not Allowed\r\n");
        }else if(request.getMethod().equals("PATCH")){
            response.append("204 No Content\r\n");
            FileOutputStream patchFile = new FileOutputStream(new File(ServerSettings.getDirectory(), request.getPath()));
            patchFile.write(request.getBody().getBytes());
        }else {
            response.append("200 OK\r\n");
        }
        response.append("\r\n");

        response.append(ServerReader.readFileContents(new File(ServerSettings.getDirectory(), request.getPath())));

        return response.toString().getBytes();
    }
}
