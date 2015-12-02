package main;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger("ServerLogger");

    public static void main(String[] args) {
        ServerSettings.parse(args);
        ServerSettings.setUpLogger();
        ServerSettings.buildRoutes();

        try {
            HttpServer http = new HttpServer(ServerSettings.getPort());
            logger.log(Level.INFO, "Server starting on port " + ServerSettings.getPort());
            http.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "ERROR: Could not start server on port " + ServerSettings.getPort());
        }
    }
}
