package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Router.AbstractRouter;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by nystrom on 11/24/15.
 */
public class AuthHandler extends AbstractRouter {
    private String challenge = "Default";
    private byte[] authorization;

    public AuthHandler(String user, String password, String challenge) {
        this.authorization = Base64.getEncoder().encode((user + ":" + password).getBytes());
        this.challenge = challenge;
    }

    public Response handle(Request request) throws IOException {
        String authHeader = "Basic " + new String(this.authorization);
        if(authHeader.equals(request.getHeader("Authorization"))) {
            return nextRouter.handle(request);
        }

        return new Response.Builder(401, "Authentication required")
                    .addHeader("WWW-Authenticate", "Basic realm=\"" + challenge + "\"").build();


    }

}
