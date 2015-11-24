package main.Handlers;

import main.Handlers.RequestHandler;
import main.Request;
import main.ServerReader;
import main.ServerSettings;

import java.io.IOException;
import java.io.File;
import java.util.Arrays;

/**
 * Created by nystrom on 11/24/15.
 */
public class FileRangeReader implements RequestHandler {

    @Override
    public byte[] handle(Request request) throws IOException {
        StringBuffer response = new StringBuffer();

        String body = ServerReader.readFileContents(new File(ServerSettings.getDirectory(), request.getPath()));

        response.append("HTTP/1.1 206 Partial Content\r\n\r\n");

        String ranges = request.getHeader("Range").split("=")[1];
        String[] range = ranges.split("-");

        if(ranges.startsWith("-")){
            response.append(body.substring(body.length()-Integer.parseInt(range[1])));
        }else if (ranges.endsWith("-")){
            response.append(body.substring(Integer.parseInt(range[0])));
        }else {
            response.append(body.substring(Integer.parseInt(range[0]), (Integer.parseInt(range[1])+1)));
        }

        return response.toString().getBytes();
    }
}
