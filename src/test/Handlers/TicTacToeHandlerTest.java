package test.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Handlers.TicTacToeHandler;
import http.Router.AbstractRouter;
import main.Boards.Board;
import main.Boards.ThreeByThreeBoard;
import main.Models.GameModel;
import main.Players.GameToken;
import main.Players.Human;
import org.junit.Test;
import test.Mocks.MockBoardRenderer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

//public class RealBoardRenderer implements Renderer {
//
//    public String render(Board board){
//
//        return board.toString();
//    }
//}

/**
 * Created by nystrom on 12/9/15.
 */
public class TicTacToeHandlerTest {
    Board board = new ThreeByThreeBoard();
    AbstractRouter handler = new TicTacToeHandler(new GameModel(board, new Human(GameToken.X), new Human(GameToken.O)), new MockBoardRenderer());

    @Test
    public void testShowsAllEmptyBoardSpots() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/tictactoe"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertEquals("---------",new String(response.body));
    }

    @Test
    public void testMakesMoveAt1AndReturns() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/tictactoe?move=1"), "HTTP/1.1");

        Response response = handler.handle(request);

        assertEquals("X--------", new String(response.body));
//        assertEquals(board.getMove(1), GameToken.X);
    }


    @Test
    public void testMakesMoveAt2AndReturns() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/tictactoe?move=2"), "HTTP/1.1");

        Response response = handler.handle(request);

        request = new Request("GET", new URI("/tictactoe?move=4"), "HTTP/1.1");

        response = handler.handle(request);

        assertEquals("-X-O-----", new String(response.body));
    }

}
