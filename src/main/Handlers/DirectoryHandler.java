package main.Handlers;

import main.FileUtil;
import main.Request;
import main.Response;
import main.ServerSettings;

import java.io.IOException;

/**
 * Created by nystrom on 12/3/15.
 */
public class DirectoryHandler implements Requestable {
    Response response = new Response();

    @Override
    public byte[] getResponse(Request request) throws IOException {
        response.setStatus("200 OK");

        String files = FileUtil.getDirectoryFileList(ServerSettings.getRootDirectory() + request.getPath());
        String body = "<!DOCTYPE html><html><head></head><body><ul>";
        for(String file : files.split(" ")){
            body += "<li><a href=\"/" + file +"\">" + file + "</a></li>";
        }
        body += "</ul></body>";

        response.setBody(body.getBytes());

        return response.toByteArray();
    }
}
