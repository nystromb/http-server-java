package http.Router;

import http.Builders.Request;
import http.Builders.Response;
import http.Builders.Route;
import http.Handlers.NotFoundHandler;
import http.Handlers.Resource;

import java.io.IOException;

/**
 * Created by nystrom on 12/9/15.
 */
public class Router extends AbstractRouter {
    protected static AbstractRouter nextRouter = new NotFoundHandler();

    public void buildRoute(Request request){
        AbstractRouter last = nextRouter;

        Route route = routes.getOrDefault(request.getPath(), new Route(new Resource()));

        if(route.isAuthenticated()){
            last.setNext(route.authentication);
            last = route.authentication;
        }

        if(route.isRedirected()){
            last.setNext(route.redirect);
            last = route.redirect;
        }

        if(route.supportsEncoding()){
            last.setNext(route.encoding);
            last = route.encoding;
        }

        last.setNext(route.handler);
    }

    public Response handle(Request request) throws IOException {
        return nextRouter.handle(request);
    }
}