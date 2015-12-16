package http;

import http.builders.Request;
import http.builders.Response;

import java.io.IOException;
import java.util.Base64;

public class Authorization implements RequestHandler {
    private RequestHandler handler;
    private String challenge = "Default";
    private byte[] authorization;

    public Authorization(String user, String password, String challenge, RequestHandler handler) {
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
