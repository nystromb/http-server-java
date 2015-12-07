package main.Handlers;

import main.*;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by nystrom on 11/24/15.
 */
public class AuthHandler extends DynamicRouter implements HttpExchange {

    private byte[] authorization;
    private String challenge = "Default";
    Response response;

    public AuthHandler(String authorization){
        this.authorization = Base64.getEncoder().encode(authorization.getBytes());
    }

    public AuthHandler(String user, String password, String challenge) {
        this.authorization = Base64.getEncoder().encode((user + ":" + password).getBytes());
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
