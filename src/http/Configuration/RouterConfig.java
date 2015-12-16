package http.configuration;


import http.*;
import http.handlers.Authorization;
import http.handlers.DirectoryHandler;
import http.handlers.FileHandler;
import http.myhandlers.LogsHandler;
import http.myhandlers.ParameterHandler;
import http.myhandlers.RedirectHandler;
import http.myhandlers.Resource;
import http.router.Route;
import http.router.Router;
import http.views.BoardRenderer;
import main.Boards.ThreeByThreeBoard;
import main.Models.GameModel;
import main.Players.GameToken;
import main.Players.Human;
import main.Players.UnbeatablePlayer;

public class RouterConfig {
    public static void setUpRoutes() {
        Router.addRoute(new Route("/", new DirectoryHandler()));

        Router.addRoute(new Route("/file1", new FileHandler()));

        Router.addRoute(new Route("/file2", new FileHandler()));

        Router.addRoute(new Route("/image.png", new FileHandler()));

        Router.addRoute(new Route("/image.jpeg", new FileHandler()));

        Router.addRoute(new Route("/image.gif", new FileHandler()));

        Router.addRoute(new Route("/text-file.txt", new FileHandler()));

        Router.addRoute(new Route("/partial_content.txt", new FileHandler()));

        Router.addRoute(new Route("/patch-content.txt", new FileHandler()));

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
