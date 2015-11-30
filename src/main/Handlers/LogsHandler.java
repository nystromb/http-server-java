package main.Handlers;

import main.Handlers.RequestHandler;
import main.Request;
import main.ServerReader;
import main.ServerSettings;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.io.File;
/**
 * Created by nystrom on 11/24/15.
 */
public class LogsHandler implements RequestHandler {
    private byte[] authorization;

    public LogsHandler(String authorization){
        this.authorization = Base64.getEncoder().encode(authorization.getBytes());
    }


    @Override
    public byte[] handle(Request request) throws IOException {
        ByteArrayOutputStream response = new ByteArrayOutputStream();

        String authHeader = "Basic " + new String(this.authorization, "UTF-8");

        if (request.hasHeader("Authorization") && authHeader.equals(request.getHeader("Authorization"))) {
            response.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
            byte[] body = ServerReader.readImageContents(new File(ServerSettings.getDirectory() + "logs/logs.txt"));
            response.write(body);
        } else {
            response.write("HTTP/1.1 401 Authorization Required\r\n\r\nAuthentication required".getBytes());
        }


        return response.toByteArray();
    }

}
