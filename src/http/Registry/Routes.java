package http.registry;


import http.*;
import http.builders.Route;
import http.views.BoardRenderer;
import main.Boards.ThreeByThreeBoard;
import main.Models.GameModel;
import main.Players.GameToken;
import main.Players.Human;
import main.Players.UnbeatablePlayer;

import java.util.HashMap;

public class Routes extends HashMap<String, Route> {
    public Routes () {
        this.put("/", new Route(
                new DirectoryHandler()));

        this.put("/file1", new Route(
                new FileHandler()));

        this.put("/file2", new Route(
                new FileHandler()));

        this.put("/image.png", new Route(
                new FileHandler()));

        this.put("/image.jpeg", new Route(
                new FileHandler()));

        this.put("/image.gif", new Route(
                new FileHandler()));

        this.put("/text-file.txt", new Route(
                new FileHandler()));

        this.put("/partial_content.txt", new Route(
                new FileHandler()));

        this.put("/patch-content.txt", new Route(
                new FileHandler()));

        this.put("/logs", new Route(
                new Authorization("admin", "hunter2", "secret",
                    new LogsHandler())));

        this.put("/form", new Route(
                new Resource()));

        this.put("/parameters", new Route(
                new ParameterHandler()));

        this.put("/method_options", new Route(
                new Resource()));

        this.put("/redirect", new Route(
                new RedirectHandler("/")));

        this.put("/tictactoe", new Route(
                new GameHandler(
                    new GameModel(
                        new ThreeByThreeBoard(),
                        new Human(GameToken.X),
                        new Human(GameToken.O)),
                    new BoardRenderer())));

        UnbeatablePlayer computer = new UnbeatablePlayer();
        computer.setPiece(GameToken.O);
        this.put("/unbeatable", new Route(
                new GameHandler(
                        new GameModel(
                                new ThreeByThreeBoard(),
                                new Human(GameToken.X),
                                computer),
                        new BoardRenderer())));
    }
}
