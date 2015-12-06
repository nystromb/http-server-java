package main;

import main.Handlers.*;

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
        Router.addRoute("/", new DirectoryHandler());
        Router.addRoute("/file1", new FileHandler());
        Router.addRoute("/file2", new FileHandler());
        Router.addRoute("/text-file.txt", new FileHandler());
        Router.addRoute("/image.jpeg", new FileHandler());
        Router.addRoute("/image.png", new FileHandler());
        Router.addRoute("/image.gif", new FileHandler());
        Router.addRoute("/logs", new LogsHandler("admin:hunter2"));
        Router.addRoute("/foobar", new NotFoundHandler());
        Router.addRoute("/redirect", new RedirectHandler());
        Router.addRoute("/parameters", new ParameterHandler());
        Router.addRoute("/partial_content.txt", new FileHandler());
        Router.addRoute("/patch-content.txt", new FileHandler());
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
