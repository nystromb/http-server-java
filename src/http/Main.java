package http;

import http.configuration.Settings;
import http.server.HttpServer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger( Main.class.getName() );

    public static void main(String[] args) {
        Settings.parse(args);
        try {
            HttpServer http = new HttpServer(Settings.getPort());
            logger.log(Level.INFO, "Server starting on port " + Settings.getPort());
            http.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not start server on port " + Settings.getPort());
        }
    }
}
