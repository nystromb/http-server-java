package main;

import main.Handlers.*;

import java.nio.file.Paths;
import java.util.Hashtable;

/**
 * Created by nystrom on 11/5/15.
 */
public class Router {
    static Hashtable<String, HttpExchange> routes = new Hashtable();

    public static void addRoute(String path, HttpExchange action){
        routes.put(path, action);
    }

    public static HttpExchange getHandler(Request request) {
        return routes.getOrDefault(Paths.get(request.getPath()).toString(), new Resource());
    }
}
