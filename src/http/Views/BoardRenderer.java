package http.Views;

import http.Builders.Request;
import main.Models.GameModel;
import main.Players.GameToken;

/**
 * Created by nystrom on 12/9/15.
 */
public class BoardRenderer implements Renderer {
    @Override
    public String render(Request request, GameModel game) {
        StringBuffer boardString = new StringBuffer();
        boardString.append("<html>\n" +
                "  <head>" +
                "<style>" +
                    "li {" +
                        "float: left;" +
                        "width: 30px;" +
                    "}" +
                "</style>" +
                "</head>\n" +
                "  <body>\n" +
                    "</style>" +
                "    <form action=\"" + request.getPath() + "\" method=\"get\">\n" +
                "      <input type=\"number\" min=\"1\" max=\"9\" name=\"move\">\n" +
                "      <button>Make move</button>\n" +
                "    </form>");
        if(game.isOver()) boardString.append("Game Over!</br>");
        if(game.board.win(GameToken.O)) boardString.append("O wins!");
        if(game.board.win(GameToken.X)) boardString.append("X wins!");
        boardString.append("<ul style=\"list-style:none;margin:0;padding:0;\">");
        for(int spot = 1; spot <= game.board.getCellCount(); spot++){
            switch(game.board.getMove(spot)){
                case X:
                    boardString.append("<li>"+GameToken.X+"</li>");
                    break;
                case O:
                    boardString.append("<li>"+GameToken.O+"</li>");
                    break;
                case EMPTY:
                    boardString.append("<li>"+"-"+"</li>");
                    break;
            }
            if((spot % 3) == 0) boardString.append("</br>");
        }
        boardString.append("</ul>");
        boardString.append("<form action=\"#\" method=\"get\">\n" +
                "          <button name=\"clear\" value=\"true\">Reset Board</button>\n" +
                "    </form>");
        boardString.append("  </body>\n" +
                "</html>");
        return boardString.toString();
    }
}
