package http;

import http.configuration.Settings;
import http.handlers.Authorization;
import http.myhandlers.LogsHandler;
import http.myhandlers.ParameterHandler;
import http.myhandlers.RedirectHandler;
import http.myhandlers.Resource;
import http.router.Route;
import http.router.Router;
import http.server.HttpServer;
import http.views.BoardRenderer;
import main.Boards.ThreeByThreeBoard;
import main.Models.GameModel;
import main.Players.GameToken;
import main.Players.Human;
import main.Players.UnbeatablePlayer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger( "http.log" );

    public static void main(String[] args) {
        Settings.parse(args);
        Settings.setUpLogger();
        Settings.createRoutes();
        addMyRoutes();

        try {
            HttpServer http = new HttpServer(Settings.PORT);
            logger.log(Level.INFO, "Server starting on port " + Settings.PORT);
            http.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Could not start server on port " + Settings.PORT);
        }
    }

    private static void addMyRoutes() {
        Router.addRoute(new Route("/logs", new Authorization("admin", "hunter2", "secret", new LogsHandler())));

        Router.addRoute(new Route("/form", new Resource()));

        Router.addRoute(new Route("/parameters", new ParameterHandler()));

        Router.addRoute(new Route("/method_options", new Resource()));

        Router.addRoute(new Route("/redirect", new RedirectHandler("/")));

        Router.addRoute(new Route("/tictactoe",
                new GameHandler(
                        new GameModel(
                                new ThreeByThreeBoard(),
                                new Human(GameToken.X),
                                new Human(GameToken.O)),
                        new BoardRenderer())));

        UnbeatablePlayer computer = new UnbeatablePlayer();
        computer.setPiece(GameToken.O);
        Router.addRoute(new Route("/unbeatable",
                new GameHandler(
                        new GameModel(
                                new ThreeByThreeBoard(),
                                new Human(GameToken.X),
                                computer),
                        new BoardRenderer())));
    }
}
