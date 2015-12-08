package main.Handlers;

import main.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import main.Builders.Request;
import main.Builders.Response;
import main.Builders.Response.Builder;

/**
 * Created by nystrom on 12/3/15.
 */
public class FileHandler implements HttpExchange {
    Builder response = new Builder();

    @Override
    public Response exchange(Request request) throws IOException {
        switch(request.getMethod()){
            case "GET":
                if(request.hasHeader("Range")) {
                    response.status(206);

                    String ranges = request.getHeader("Range").split("=")[1];
                    String[] range = ranges.split("-");

                    String contents = FileUtil.readFileContents(new File(ServerSettings.getRootDirectory(), request.getPath()));
                    if(ranges.startsWith("-")){
                        response.setBody(contents.substring(contents.length() - Integer.parseInt(range[1])));
                    }else if (ranges.endsWith("-")){
                        response.setBody(contents.substring(Integer.parseInt(range[0])));
                    }else {
                        response.setBody(contents.substring(Integer.parseInt(range[0]), (Integer.parseInt(range[1]) + 1)));
                    }
                }
                else {
                    response.status(200).setBody(FileUtil.readFileContents(request.getPath()));
                }
                break;
            case "PUT":
            case "POST":
                response.status(405);
                break;
            case "PATCH":
                response.status(204);
                Files.write(new File(ServerSettings.getRootDirectory() + request.getPath()).toPath(), request.getBody().getBytes());
                break;
        }

        return response.build();
    }
}
