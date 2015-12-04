package main.Handlers;

import main.FileUtil;
import main.Request;
import main.Response;

import java.io.IOException;

/**
 * Created by nystrom on 12/3/15.
 */
public class FileHandler implements Requestable {
    Response response = new Response();

    @Override
    public Response getResponse(Request request) throws IOException {
        switch(request.getMethod()){
            case "GET":
                response.setStatus("200 OK");
                response.setBody(FileUtil.readFileContents(request.getPath()));
                break;
            case "PUT":
            case "POST":
                response.setStatus("405 Method Not Allowed");
                response.setBody("Method Not Allowed".getBytes());
                break;
        }
        return response;
    }
}
