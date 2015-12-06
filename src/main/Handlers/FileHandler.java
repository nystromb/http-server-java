package main.Handlers;

import main.FileUtil;
import main.Request;
import main.Response;
import main.ServerSettings;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Created by nystrom on 12/3/15.
 */
public class FileHandler implements Requestable {
    Response response = new Response();
    String patchData = "";
    @Override
    public byte[] getResponse(Request request) throws IOException {
        switch(request.getMethod()){
            case "GET":
                if(request.hasHeader("Range")) {
                    response.setStatus("206 Partial Content");

                    String ranges = request.getHeader("Range").split("=")[1];
                    String[] range = ranges.split("-");
                    String contents = FileUtil.readFileContents(new File(ServerSettings.getRootDirectory(), request.getPath()));
                    if(ranges.startsWith("-")){
                        response.setBody(contents.substring(contents.length() - Integer.parseInt(range[1])));
                    }else if (ranges.endsWith("-")){
                        response.setBody(contents.substring(Integer.parseInt(range[0])));
                    }else {
                        response.setBody(" " + contents.substring(Integer.parseInt(range[0]), (Integer.parseInt(range[1]) + 1)));
                    }
                }
                else {
                    response.setStatus("200 OK");
                    response.setBody(FileUtil.readFileContents(request.getPath()));
                }
                break;
            case "PUT":
            case "POST":
                response.setStatus("405 Method Not Allowed");
                response.setBody("Method Not Allowed".getBytes());
                break;
            case "PATCH":
                response.setStatus("204 Patch Content");
                Files.write(new File(ServerSettings.getRootDirectory() + request.getPath()).toPath(), request.getBody().getBytes());
                break;
        }

        return response.toByteArray();
    }
}
