package test.Handlers;

import main.Boards.Board;
import main.Boards.ThreeByThreeBoard;
import main.Players.GameToken;
import main.Views.BoardRenderer;
import org.junit.Test;
import test.Renderer;

import static org.junit.Assert.assertEquals;

/**
 * Created by nystrom on 12/9/15.
 */
public class BoardRendererTest {

    public int countTokens(String html, String token) {
        return html.split(token).length - 1;
    }

    @Test
    public void test(){
        Renderer renderer = new BoardRenderer();
        Board board = new ThreeByThreeBoard();
        board.putMove(3, GameToken.X);
        board.putMove(1, GameToken.X);
        board.putMove(2, GameToken.O);
        String boardHtml = renderer.render(board);

        assertEquals(2, countTokens(boardHtml, "X"));
        assertEquals(1, countTokens(boardHtml, "O"));
    }
}
