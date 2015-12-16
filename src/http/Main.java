package http;

import http.configuration.Settings;
import http.server.HttpServer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger( "http.log" );

    public static void main(String[] args) {
        Settings.parse(args);
        Settings.setUpLogger();
        Settings.createRoutes();
        try {
            HttpServer http = new HttpServer(Settings.PORT);
            logger.log(Level.INFO, "Server starting on port " + Settings.PORT);
            http.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not start server on port " + Settings.PORT);
        }
    }
}
