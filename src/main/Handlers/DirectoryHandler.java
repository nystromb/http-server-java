package main.Handlers;

import java.io.IOException;

import main.Assets.FileUtil;
import main.Builders.Request;
import main.Builders.Response;
import main.Builders.Response.Builder;
import main.Configuration.ServerSettings;

/**
 * Created by nystrom on 12/3/15.
 */
public class DirectoryHandler implements HttpExchange {
    Builder response = new Builder();
    @Override
    public Response exchange(Request request) throws IOException {
        String files = FileUtil.getDirectoryFileList(ServerSettings.getRootDirectory() + request.getPath());
        String contents = "<!DOCTYPE html><html><head></head><body><ul>";
        for(String file : files.split(" ")){
            contents += "<li><a href=\"/" + file +"\">" + file + "</a></li>";
        }
        contents += "</ul></body>";

        return response.status(200).setBody(contents).build();
    }
}
