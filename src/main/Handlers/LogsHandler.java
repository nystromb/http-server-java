package main.Handlers;

import main.Handlers.RequestHandler;
import main.Request;
import main.Response;
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
    Response response = new Response();

    public LogsHandler(String authorization){
        this.authorization = Base64.getEncoder().encode(authorization.getBytes());
    }

    public Response handle(Request request) throws IOException {
        String authHeader = "Basic " + new String(this.authorization);

        if (request.hasHeader("Authorization") && authHeader.equals(request.getHeader("Authorization"))) {
            response.setStatus("200 OK");
            byte[] body = ServerReader.readImageContents(new File(ServerSettings.getDirectory() + "logs/logs.txt"));
            response.setBody(body);
        } else {
            response.setStatus("401 Authorization Required");
            response.setBody("Authentication required".getBytes());
        }
        
        return response;
    }

}
