package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Registry.Routes;
import http.Router.Router;

/**
 * Created by nystrom on 11/5/15.
 */
public class NotFoundHandler extends Router {
    private static Routes routes = new Routes();

    public void handle(Request request) {
        if(!routes.containsKey(request.getPath())){
            Response response = new Response.Builder(404).build();
        }else{
            nextRouter.handle(request);
        }
    }
}
