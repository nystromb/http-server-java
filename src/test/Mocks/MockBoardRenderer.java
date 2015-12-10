package test.Mocks;

import main.Boards.Board;
import test.Renderer;

public class MockBoardRenderer implements Renderer {

    public String render(Board board){
        StringBuffer boardString = new StringBuffer();

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

        return boardString.toString();
    }
}
