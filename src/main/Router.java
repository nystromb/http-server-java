package main;

import main.Handlers.*;

import java.nio.file.Paths;
import java.util.Hashtable;

/**
 * Created by nystrom on 11/5/15.
 */
public class Router {
    static Hashtable<String, Requestable> routes = new Hashtable();

    public static void addRoute(String path, Requestable action){
        routes.put(path, action);
    }

    public static Requestable getHandler(Request request) {
        if(request.hasHeader("Range")){
            return new PartialContentHandler();
        }
        return routes.getOrDefault(Paths.get(request.getPath()).toString(), new Resource());
    }
}
