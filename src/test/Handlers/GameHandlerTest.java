package test.handlers;

import http.handlers.ApplicationController;
import http.request.Request;
import http.response.Response;
import http.GameHandler;
import main.Boards.Board;
import main.Boards.ThreeByThreeBoard;
import main.Models.GameModel;
import main.Players.GameToken;
import main.Players.Human;
import main.Players.UnbeatablePlayer;
import org.junit.Before;
import org.junit.Test;
import test.mocks.MockBoardRenderer;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class GameHandlerTest {
    Board board = new ThreeByThreeBoard();
    ApplicationController handler;
    @Before
    public void setUp(){
        handler = new GameHandler(new GameModel(board, new Human(GameToken.X), new Human(GameToken.O)), new MockBoardRenderer());
    }

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

    @Test
    public void testDoesntMakeMoveIfGameIsOver() throws IOException, URISyntaxException {
        Request move1 = new Request("GET", new URI("/tictactoe?move=1"), "HTTP/1.1");
        Request move2 = new Request("GET", new URI("/tictactoe?move=5"), "HTTP/1.1");
        Request move3 = new Request("GET", new URI("/tictactoe?move=2"), "HTTP/1.1");
        Request move4 = new Request("GET", new URI("/tictactoe?move=6"), "HTTP/1.1");
        Request move5 = new Request("GET", new URI("/tictactoe?move=3"), "HTTP/1.1");
        Request move6 = new Request("GET", new URI("/tictactoe?move=4"), "HTTP/1.1");

        handler.handle(move1);
        handler.handle(move2);
        handler.handle(move3);
        handler.handle(move4);
        handler.handle(move5);

        Response response = handler.handle(move6);
        assertEquals(0,new String(response.body).split("O").length-3);

        Request clearBoard = new Request("GET", new URI("/tictactoe?clear=true"), "HTTP/1.1");
        response = handler.handle(clearBoard);
        assertEquals("---------", new String(response.body));
    }


    @Test
    public void testComputerPlaysMoveIfItsHisTurn() throws URISyntaxException, IOException {
        UnbeatablePlayer unbeatablePlayer = new UnbeatablePlayer();
        unbeatablePlayer.setPiece(GameToken.O);
        handler = new GameHandler(new GameModel(new ThreeByThreeBoard(), new Human(GameToken.X), unbeatablePlayer), new MockBoardRenderer());

        Request request = new Request("GET", new URI("/unbeatable?move=1"), "HTTP/1.1");
        Response response = handler.handle(request);

        assertEquals("X---O----", new String(response.body));
    }
}
