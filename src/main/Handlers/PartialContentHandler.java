package main.Handlers;

import main.FileUtil;
import main.Request;
import main.ServerSettings;
import java.io.File;
import java.io.IOException;

/**
 * Created by nystrom on 12/5/15.
 */
public class PartialContentHandler implements Requestable {
    @Override
    public byte[] getResponse(Request request) throws IOException {
        StringBuffer response = new StringBuffer();

        String body = FileUtil.readFileContents(new File(ServerSettings.getRootDirectory(), request.getPath()));

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
