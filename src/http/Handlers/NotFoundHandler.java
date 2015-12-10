package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Router.AbstractRouter;

import java.io.IOException;

/**
 * Created by nystrom on 11/5/15.
 */
public class NotFoundHandler extends AbstractRouter {
    protected static AbstractRouter nextRouter;

    public void setNext(AbstractRouter next) {
        nextRouter = next;
    }

    public Response handle(Request request) throws IOException {
        if(!routes.containsKey(request.getPath())){
            return new Response.Builder(404).build();
        }else{
            return nextRouter.handle(request);
        }
    }
}
