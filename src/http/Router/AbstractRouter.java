package http.Router;

import http.Builders.Request;
import http.Builders.Response;
import http.Handlers.NotFoundHandler;
import http.Registry.Routes;

import java.io.IOException;

/**
 * Created by nystrom on 12/9/15.
 */
public abstract class AbstractRouter {
    protected static Routes routes = new Routes();
    protected static AbstractRouter nextRouter = new NotFoundHandler();

    public void setNext(AbstractRouter next) {
        nextRouter = next;
    }

    public abstract Response handle(Request request) throws IOException;
}
