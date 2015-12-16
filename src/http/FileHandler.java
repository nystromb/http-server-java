package http;

import http.assets.FileUtil;
import http.builders.Request;
import http.builders.Response;
import http.configuration.Settings;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileHandler extends ApplicationController {
    Response.Builder response = new Response.Builder();

    @Override
    protected Response get(Request request) throws IOException {
        if(request.hasHeader("Range")) {
            response.status(206);

            String ranges = request.getHeader("Range").split("=")[1];
            String[] range = ranges.split("-");

            String contents = FileUtil.readFileContents(new File(Settings.getRootDirectory(), request.getPath()));
            if(ranges.startsWith("-")){
                response.setBody(contents.substring(contents.length() - Integer.parseInt(range[1])));
            }else if (ranges.endsWith("-")){
                response.setBody(contents.substring(Integer.parseInt(range[0])));
            }else {
                response.setBody(contents.substring(Integer.parseInt(range[0]), (Integer.parseInt(range[1]) + 1)));
            }
        } else {
            response.status(200).setBody(FileUtil.readFileContents(request.getPath()));
        }

        return response.build();
    }

    @Override
    protected Response put(Request request){
        return response.status(405).build();
    }

    @Override
    protected Response post(Request request){
        return response.status(405).build();
    }

    @Override
    protected Response patch(Request request) throws IOException {
        response.status(204);
        Files.write(new File(Settings.getRootDirectory() + request.getPath()).toPath(), request.getBody().getBytes());
        return response.build();
    }
}
