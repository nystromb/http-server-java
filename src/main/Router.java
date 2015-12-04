package main;

import main.Handlers.DirectoryHandler;
import main.Handlers.FileHandler;
import main.Handlers.Requestable;
import main.Handlers.Resource;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Hashtable;
import java.io.File;

/**
 * Created by nystrom on 11/5/15.
 */
public class Router {
    static Hashtable<String, Requestable> routes = new Hashtable();

    public static void addRoute(String path, Requestable action){
        routes.put(path, action);
    }

    public static Requestable getHandler(Request request) {
        Path targetPath = new File(ServerSettings.getRootDirectory(), request.getPath()).toPath();

        if(routes.containsKey(request.getPath())){
            return routes.get(request.getPath());
        }else if(Files.isDirectory(targetPath)){
            return new DirectoryHandler();
        }else if (Files.isReadable(targetPath)){
            return new FileHandler();
        }else{
            return new Resource();
        }
    }
}
