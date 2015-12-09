package http;

import http.Configuration.Settings;
import http.Server.HttpServer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger("ServerLogger");

    public static void main(String[] args) {
        Settings.parse(args);
        try {
            setUpLogger();
            HttpServer http = new HttpServer(Settings.getPort());
            logger.log(Level.INFO, "Server starting on port " + Settings.getPort());
            http.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "ERROR: Could not start server on port " + Settings.getPort());
        }
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