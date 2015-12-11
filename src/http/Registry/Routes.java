package http.Registry;


import http.Builders.Route;
import http.Handlers.*;
import main.Boards.ThreeByThreeBoard;
import main.Models.GameModel;
import main.Players.GameToken;
import main.Players.Human;
import http.Views.BoardRenderer;
import main.Players.UnbeatablePlayer;

import java.util.HashMap;

/**
 * Created by nystrom on 12/8/15.
 */
public class Routes extends HashMap<String, Route> {
    public Routes () {
        put("/", new Route(
                new DirectoryHandler()));

        put("/file1", new Route(
                new FileHandler()));

        put("/file2", new Route(
                new FileHandler()));

        put("/image.png", new Route(
                new FileHandler()));

        put("/image.jpeg", new Route(
                new FileHandler()));

        put("/image.gif", new Route(
                new FileHandler()));

        put("/text-file.txt", new Route(
                new FileHandler()));

        put("/partial_content.txt", new Route(
                new FileHandler()));

        put("/patch-content.txt", new Route(
                new FileHandler()));

        put("/logs", new Route(
                new Authorization("admin", "hunter2", "Challenge",
                        new LogsHandler())));

        put("/form", new Route(
                new Resource()));

        put("/parameters", new Route(
                new ParameterHandler()));

        put("/method_options", new Route(
                new Resource()));

        put("/redirect", new Route(
                new RedirectHandler("/")));

        put("/tictactoe", new Route(
                new GameHandler(
                    new GameModel(
                        new ThreeByThreeBoard(),
                        new Human(GameToken.X),
                        new Human(GameToken.O)),
                    new BoardRenderer())));

        UnbeatablePlayer computer = new UnbeatablePlayer();
        computer.setPiece(GameToken.O);
        put("/unbeatable", new Route(
                new GameHandler(
                        new GameModel(
                                new ThreeByThreeBoard(),
                                new Human(GameToken.X),
                                computer),
                        new BoardRenderer())));
    }
}
