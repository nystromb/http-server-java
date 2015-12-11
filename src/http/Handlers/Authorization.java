package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by nystrom on 11/24/15.
 */
public class Authorization implements Router {
    private String challenge;
    private byte[] authorization;
    private Router handler;

    public Authorization(String user, String password, String challenge, Router handler) {
        this.authorization = Base64.getEncoder().encode((user + ":" + password).getBytes());
        this.challenge = challenge;
        this.handler = handler;
    }

    public Response handle(Request request) throws IOException {
        String authHeader = "Basic " + new String(this.authorization);
        if(authHeader.equals(request.getHeader("Authorization"))) {
            return handler.handle(request);
        }

        return new Response.Builder(401, "Authentication required")
                    .addHeader("WWW-Authenticate", "Basic realm=\"" + challenge + "\"").build();


    }

}
