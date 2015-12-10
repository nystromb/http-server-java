package test.Mocks;

import main.Models.GameModel;
import http.Views.Renderer;

public class MockBoardRenderer implements Renderer {

    public String render(GameModel game){
        StringBuffer boardString = new StringBuffer();

        for(int spot = 1; spot <= game.board.getCellCount(); spot++){
            switch(game.board.getMove(spot)){
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

        return boardString.toString();
    }
}
