package main.Handlers;

import main.*;

import java.io.IOException;

/**
 * Created by nystrom on 12/3/15.
 */
public class DirectoryHandler implements HttpExchange {
    Response response;

    @Override
    public Response exchange(Request request) throws IOException {
        String files = FileUtil.getDirectoryFileList(ServerSettings.getRootDirectory() + request.getPath());
        String body = "<!DOCTYPE html><html><head></head><body><ul>";
        for(String file : files.split(" ")){
            body += "<li><a href=\"/" + file +"\">" + file + "</a></li>";
        }
        body += "</ul></body>";

        response = new Response.Builder(200, body).build();
        return response;
    }
}
