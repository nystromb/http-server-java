package main;

import main.Configuration.ServerSettings;
import main.Server.HttpServer;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger("ServerLogger");

    public static void main(String[] args) {
        ServerSettings.parse(args);
        try {
            setUpLogger();
            HttpServer http = new HttpServer(ServerSettings.getPort());
            logger.log(Level.INFO, "Server starting on port " + ServerSettings.getPort());
            http.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "ERROR: Could not start server on port " + ServerSettings.getPort());
        }
    }

    public static void setUpLogger(){
        try {
            FileHandler fileHandler = new FileHandler("logs/logfile.txt", true);
            Main.logger.addHandler(fileHandler);
        } catch(IOException e){
            Main.logger.log(Level.SEVERE, "Couldn't set up logging");
        }
    }
}