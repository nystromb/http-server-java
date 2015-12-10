package main.Views;

import main.Models.GameModel;
import main.Players.GameToken;
import test.Renderer;

/**
 * Created by nystrom on 12/9/15.
 */
public class BoardRenderer implements Renderer {
    @Override
    public String render(GameModel game) {
        StringBuffer boardString = new StringBuffer();
        boardString.append("<html>\n" +
                "  <head></head>\n" +
                "  <body>\n" +
                "    <form action=\"/tictactoe\" method=\"get\">\n" +
                "      <input name=\"move\">\n" +
                "      <button>Make move</button>\n" +
                "    </form>");
        if(game.isOver()){
            boardString.append("Game Over!</br>");
        }
        for(int spot = 1; spot <= game.board.getCellCount(); spot++){
            switch(game.board.getMove(spot)){
                case X:
                    boardString.append(GameToken.X);
                    break;
                case O:
                    boardString.append(GameToken.O);
                    break;
                case EMPTY:
                    boardString.append("-");
                    break;
            }
            if((spot % 3) == 0) boardString.append("</br>");
        }
        boardString.append("<form action=\"#\" method=\"get\">\n" +
                "          <button name=\"clear\" value=\"true\">Reset Board</button>\n" +
                "    </form>");
        boardString.append("  </body>\n" +
                "</html>");
        return boardString.toString();
    }
}
