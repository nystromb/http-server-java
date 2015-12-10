package http.Registry;


import http.Builders.Route;
import http.Handlers.TicTacToeHandler;
import main.Boards.ThreeByThreeBoard;
import main.Models.GameModel;
import main.Players.GameToken;
import main.Players.Human;
import main.Views.BoardRenderer;

import java.util.HashMap;

/**
 * Created by nystrom on 12/8/15.
 */
public class Routes extends HashMap<String, Route> {
    public Routes () {
        this.put("/tictactoe", new Route(new TicTacToeHandler(
                            new GameModel(
                                new ThreeByThreeBoard(),
                                new Human(GameToken.X),
                                new Human(GameToken.O)),
                            new BoardRenderer())));

    }
}
