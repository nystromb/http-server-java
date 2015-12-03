package main.Handlers;

import main.Request;
import main.Response;
import main.FileUtil;
import java.io.IOException;
import java.util.Base64;

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
            response.setBody(FileUtil.readFileContents("logs/logs.txt"));
        } else {
            response.setStatus("401 Authorization Required");
            response.setBody("Authentication required".getBytes());
        }
        
        return response;
    }

}
