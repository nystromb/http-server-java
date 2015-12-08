package main;

import main.Handlers.Resource;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger("ServerLogger");

    public static void main(String[] args) {
        ServerSettings.parse(args);
        try {
            setUpLogger();
            buildRoutes();
            HttpServer http = new HttpServer(ServerSettings.getPort());
            logger.log(Level.INFO, "Server starting on port " + ServerSettings.getPort());
            http.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "ERROR: Could not start server on port " + ServerSettings.getPort());
        }
    }

    public static void buildRoutes() {
        Route formRoute = new Route();
        formRoute.setHandler(new Resource());
        DynamicRouter.addRoute("/form", formRoute);

        Route methodOptions = new Route();
        methodOptions.setHandler(new Resource());
        DynamicRouter.addRoute("/method_options", methodOptions);

        Route logsRoute = new Route();
        logsRoute.setAuthentication("admin", "hunter2", "challenge");
        DynamicRouter.addRoute("/logs", logsRoute);
    }

    public static void setUpLogger(){
        try {
            java.util.logging.FileHandler fileHandler = new java.util.logging.FileHandler("logs/logfile.txt", true);
            Main.logger.addHandler(fileHandler);
        }catch(IOException e){
            Main.logger.log(Level.SEVERE, "Couldn't set up logging");
        }
    }
}
