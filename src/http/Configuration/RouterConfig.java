package http.configuration;


import http.handlers.DirectoryHandler;
import http.handlers.FileHandler;
import http.router.Route;
import http.router.Router;

import java.io.File;

public class RouterConfig {
    public static void setUpRoutes() {
        Router.addRoute(new Route("/", new DirectoryHandler()));
        File root = new File(Settings.PUBLIC_DIR);
        recurseDirectory(root);
    }

    private static void recurseDirectory(File directory) {
        File[] list = directory.listFiles();

        for(File file : list){
            if(file.isFile()){
                Router.addRoute(new Route("/" + file.getName(), new FileHandler()));
            }
        }
    }
}
