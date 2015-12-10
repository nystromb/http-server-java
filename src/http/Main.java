package http;

import http.Configuration.Settings;
import http.Server.HttpServer;

import java.io.IOException;
import java.util.logging.FileHandler;
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
            logger.log(Level.SEVERE, "ERROR: Could not start server on port " + Settings.getPort());
        }
    }
}
