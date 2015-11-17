package main;

import java.util.Hashtable;
import java.io.File;

/**
 * Created by nystrom on 11/5/15.
 */
public class Router {
    static Hashtable<String, RequestHandler> routes = new Hashtable();

    public static void addRoute(String path, RequestHandler action){
        routes.put(path, action);
    }

    public static RequestHandler route(Request request) {
        if(routes.containsKey(request.getPath())){
            return routes.get(request.getPath());
        }else {
            return new Error404();
        }
    }
}
