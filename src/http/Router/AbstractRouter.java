package http.Router;

import http.Builders.Request;
import http.Builders.Response;

import java.io.IOException;

/**
 * Created by nystrom on 12/9/15.
 */
public abstract class AbstractRouter {
    protected static AbstractRouter nextRouter;

    public void setNext(AbstractRouter next) {
        nextRouter = next;
    }

    public abstract Response handle(Request request) throws IOException;
}
