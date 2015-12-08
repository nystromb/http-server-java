package main.Router;

import main.Builders.Request;
import main.Builders.Response;
import main.Builders.Route;
import main.Handlers.*;
import main.Handlers.HttpExchange;
import main.Registry.Routes;
import main.Configuration.ServerSettings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by nystrom on 11/5/15.
 */
public class Router {
    static HttpExchange directoryHandler = new DirectoryHandler();
    static HttpExchange fileHandler = new FileHandler();
    static HttpExchange paramHandler = new ParameterHandler();
    static HttpExchange fileNotFound = new NotFoundHandler();
    static HttpExchange redirectHandler = new RedirectHandler();

    private static Routes routes = new Routes();

    public static Route buildRoute(Request request) {
        Route route = routes.getOrDefault(request.getPath(), new Route());

        if(!routes.containsKey(request.getPath()) && Files.notExists(Paths.get(ServerSettings.getRootDirectory() + request.getPath()))){
            route.setNext(fileNotFound);
            return route;
        }

        if(request.getQuery().length() > 0){
            route.setNext(paramHandler);
        }

        if(route.handler != null){
            route.setNext(route.handler);
        } else {
            if(Files.isDirectory(Paths.get(ServerSettings.getRootDirectory() + request.getPath()).normalize())){
                route.setNext(directoryHandler);
            } else if(Files.isReadable(Paths.get(ServerSettings.getRootDirectory() + request.getPath()).normalize())) {
                route.setNext(fileHandler);
            }
        }

        return route;
    }

    public static Response route(Route route, Request request) throws IOException {
        Response response = new Response.Builder(404).build();

        for(HttpExchange handler : route.handlers){
            response = handler.exchange(request);
            if(response.statusLine.contains("200 OK")){
                continue;
            }else {
                break;
            }
        }

        return response;
    }
}
