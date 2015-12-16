package test;

import http.builders.Request;
import http.views.BoardRenderer;
import http.views.Renderer;
import main.Boards.ThreeByThreeBoard;
import main.Models.GameModel;
import main.Players.GameToken;
import main.Players.Human;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by nystrom on 12/9/15.
 */
public class BoardRendererTest {
    GameModel game;
    Request HumanTicTacToeRequest;
    Request UnbeatableTicTacToeRequest;

    @Before
    public void setUp() throws URISyntaxException {
        game = new GameModel(new ThreeByThreeBoard(), new Human(GameToken.X), new Human(GameToken.O));
        UnbeatableTicTacToeRequest = new Request("GET", new URI("/unbeatable"), "HTTP/1.1");
        HumanTicTacToeRequest = new Request("GET", new URI("/tictactoe"), "HTTP/1.1");

    }

    public int countTokens(String html, String token) {
        return html.split(token).length - 1;
    }

    @Test
    public void testPlaysGame(){
        Renderer renderer = new BoardRenderer();

        game.board.putMove(3, GameToken.X);
        game.board.putMove(1, GameToken.X);
        game.board.putMove(2, GameToken.O);
        String boardHtml = renderer.render(HumanTicTacToeRequest, game);

        assertEquals(2, countTokens(boardHtml, "X"));
        assertEquals(1, countTokens(boardHtml, "O"));
    }

    @Test
    public void testShowsInAGrid(){
        Renderer renderer = new BoardRenderer();
        game.board.putMove(3, GameToken.X);
        game.board.putMove(1, GameToken.X);
        game.board.putMove(2, GameToken.O);
        String boardHtml = renderer.render(HumanTicTacToeRequest, game);

        assertEquals(3, countTokens(boardHtml, "</br>"));
    }

    @Test
    public void testShowsGameOveWhenGameOver(){
        Renderer renderer = new BoardRenderer();

        game.board.putMove(3, GameToken.X);
        game.board.putMove(1, GameToken.X);
        game.board.putMove(2, GameToken.O);
        game.board.putMove(4, GameToken.O);
        game.board.putMove(6, GameToken.O);
        game.board.putMove(5, GameToken.X);
        game.board.putMove(7, GameToken.O);
        game.board.putMove(8, GameToken.X);
        game.board.putMove(9, GameToken.O);
        String boardHtml = renderer.render(HumanTicTacToeRequest, game);

        assertTrue(boardHtml.contains("Game Over!"));
    }

    @Test
    public void testCantPlayMovesWhenGameOver(){
        Renderer renderer = new BoardRenderer();

        game.board.putMove(3, GameToken.X);
        game.board.putMove(5, GameToken.X);
        game.board.putMove(7, GameToken.X);
        game.board.putMove(6, GameToken.O);

        String boardHtml = renderer.render(HumanTicTacToeRequest, game);

        assertEquals(3, countTokens(boardHtml, "X")-1);
        assertEquals(0, countTokens(boardHtml, "O")-2);
    }

    @Test
         public void testClearButtonShowsUp(){
        Renderer renderer = new BoardRenderer();

        String BoardHTML = renderer.render(HumanTicTacToeRequest, game);

        assertTrue(BoardHTML.contains("clear"));
    }

    @Test
    public void testShowsWinnerOWhenGameOver(){
        Renderer renderer = new BoardRenderer();

        game.board.putMove(1, GameToken.O);
        game.board.putMove(2, GameToken.O);
        game.board.putMove(3, GameToken.O);

        String BoardHTML = renderer.render(HumanTicTacToeRequest, game);
        assertTrue(BoardHTML.contains("O wins!"));
    }

    @Test
    public void testShowsWinnerXWhenGameOver(){
        Renderer renderer = new BoardRenderer();
        game.board.putMove(1, GameToken.X);
        game.board.putMove(2, GameToken.X);
        game.board.putMove(3, GameToken.X);
        String BoardHTML = renderer.render(HumanTicTacToeRequest, game);

        assertTrue(BoardHTML.contains("X wins!"));
    }
}
