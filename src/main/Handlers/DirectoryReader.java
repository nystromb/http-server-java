package main.Handlers;

import main.Request;
import main.ServerReader;
import main.ServerSettings;

import java.io.File;
import java.io.IOException;

/**
 * Created by nystrom on 11/16/15.
 */
public class DirectoryReader implements RequestHandler {
    @Override
    public byte[] handle(Request request) throws IOException {
        StringBuffer response = new StringBuffer();
        response.append("HTTP/1.1 200 OK\r\n");
        response.append("\r\n");

        response.append("<!DOCTYPE html>");
        response.append("<html>");
        response.append("<head>");
        response.append("</head>");
        response.append("<body>");
        String [] listOfFiles = ServerReader.readDirectoryContents(new File(ServerSettings.getDirectory(), request.getPath())).split(" ");
        for(String file : listOfFiles){
            response.append("<li><a href=\"/" + file + "\">" + file + "</a></li>");
        }
        response.append("</body>");
        response.append("</html>");
        return response.toString().getBytes();
    }
}
