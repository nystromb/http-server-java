package http.Registry;


import http.Builders.Route;
import http.Handlers.*;
import main.Boards.ThreeByThreeBoard;
import main.Models.GameModel;
import main.Players.GameToken;
import main.Players.Human;
import http.Views.BoardRenderer;

import java.util.HashMap;

/**
 * Created by nystrom on 12/8/15.
 */
public class Routes extends HashMap<String, Route> {
    public Routes () {
        this.put("/", new Route(new DirectoryHandler()));
        this.put("/file1", new Route(new FileHandler()));
        this.put("/file2", new Route(new FileHandler()));
        this.put("/image.png", new Route(new FileHandler()));
        this.put("/image.jpeg", new Route(new FileHandler()));
        this.put("/image.gif", new Route(new FileHandler()));
        this.put("/text-file.txt", new Route(new FileHandler()));
        this.put("/partial_content.txt", new Route(new FileHandler()));
        this.put("/patch-content.txt", new Route(new FileHandler()));
        this.put("/logs", new Route(new LogsHandler()).authenticate("admin", "hunter2", "Challenge"));
        this.put("/form", new Route(new Resource()));
        this.put("/parameters", new Route(new ParameterHandler()));
        this.put("/method_options", new Route(new Resource()));
        this.put("/redirect", new Route(new RedirectHandler("/")));

        this.put("/tictactoe", new Route(new TicTacToeHandler(
                            new GameModel(
                                new ThreeByThreeBoard(),
                                new Human(GameToken.X),
                                new Human(GameToken.O)),
                            new BoardRenderer())));

    }
}
