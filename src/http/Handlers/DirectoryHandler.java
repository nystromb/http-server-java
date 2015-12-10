package http.Handlers;

import http.Assets.FileUtil;
import http.Builders.Request;
import http.Builders.Response;
import http.Builders.Response.Builder;
import http.Configuration.Settings;
import http.Router.AbstractRouter;

import java.io.IOException;

/**
 * Created by nystrom on 12/3/15.
 */
public class DirectoryHandler extends AbstractRouter {
    Builder response = new Builder();
    @Override
    public Response handle(Request request) throws IOException {
        String files = FileUtil.getDirectoryFileList(Settings.getRootDirectory() + request.getPath());
        String contents = "<!DOCTYPE html><html><head></head><body><ul>";
        for(String file : files.split(" ")){
            contents += "<li><a href=\"/" + file +"\">" + file + "</a></li>";
        }
        contents += "</ul></body>";

        return response.status(200).setBody(contents).build();
    }
}
