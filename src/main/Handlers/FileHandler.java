package main.Handlers;

import main.FileUtil;
import java.io.File;
import main.Request;
import main.Response;
import main.ServerSettings;

import java.io.IOException;

/**
 * Created by nystrom on 12/3/15.
 */
public class FileHandler implements RequestHandler {
    Response response = new Response();

    @Override
    public Response handle(Request request) throws IOException {
        response.setStatus("200 OK");
        response.setBody(FileUtil.readFileContents(request.getPath()));
        return response;
    }
}
