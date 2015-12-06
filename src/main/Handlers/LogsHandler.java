package main.Handlers;

import main.HttpExchange;
import main.Request;
import main.Response;
import main.FileUtil;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by nystrom on 11/24/15.
 */
public class LogsHandler implements HttpExchange {
    private byte[] authorization;
    private String challenge = "ServerKey";
    Response response;

    public LogsHandler(String authorization){
        this.authorization = Base64.getEncoder().encode(authorization.getBytes());
    }

    public Response exchange(Request request) throws IOException {
        String authHeader = "Basic " + new String(this.authorization);

        if (request.hasHeader("Authorization") && authHeader.equals(request.getHeader("Authorization"))) {
            response = new Response.Builder(200, FileUtil.readFileContents("../../../Documents/workspace/HttpServer/logs/logfile.txt")).build();
        } else {
            response = new Response.Builder(401, "Authentication required")
                    .addHeader("WWW-Authenticate", "Basic realm=\"" + challenge + "\"").build();
        }
        
        return response;
    }

}
