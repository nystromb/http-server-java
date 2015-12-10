package main.Views;

import main.Boards.Board;
import test.Renderer;

/**
 * Created by nystrom on 12/9/15.
 */
public class BoardRenderer implements Renderer {
    @Override
    public String render(Board board) {
        StringBuffer boardString = new StringBuffer();
        boardString.append("<html>\n" +
                "  <head></head>\n" +
                "  <body>\n" +
                "    <form action=\"/tictactoe\" method=\"get\">\n" +
                "      <input name=\"move\">\n" +
                "      <button>Make move</button>\n" +
                "    </form>");
        for(int spot = 1; spot <= board.getCellCount(); spot++){
            switch(board.getMove(spot)){
                case X:
                    boardString.append("X");
                    break;
                case O:
                    boardString.append("O");
                    break;
                case EMPTY:
                    boardString.append("-");
                    break;
            }
        }

        boardString.append("  </body>\n" +
                "</html>");
        return boardString.toString();
    }
}
