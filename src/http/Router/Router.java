package http.Router;

import http.Builders.Request;

/**
 * Created by nystrom on 12/9/15.
 */
public abstract class Router {
    protected static Router nextRouter;

    public void setNext(Router next) {
        if(nextRouter == null){
            nextRouter = next;
        } else{
            nextRouter.setNext(next);
        }
    }

    public abstract void handle(Request request);
}
