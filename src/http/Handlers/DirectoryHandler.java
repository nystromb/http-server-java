package http.Handlers;

import java.io.IOException;

import http.Assets.FileUtil;
import http.Builders.Request;
import http.Builders.Response;
import http.Builders.Response.Builder;
import http.Configuration.Settings;

/**
 * Created by nystrom on 12/3/15.
 */
public class DirectoryHandler implements HttpExchange {
    Builder response = new Builder();
    @Override
    public Response exchange(Request request) throws IOException {
        String files = FileUtil.getDirectoryFileList(Settings.getRootDirectory() + request.getPath());
        String contents = "<!DOCTYPE html><html><head></head><body><ul>";
        for(String file : files.split(" ")){
            contents += "<li><a href=\"/" + file +"\">" + file + "</a></li>";
        }
        contents += "</ul></body>";

        return response.status(200).setBody(contents).build();
    }
}
