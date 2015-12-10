package http.Views;

import main.Boards.Board;
import main.Models.GameModel;

public interface Renderer {
    String render(GameModel board);
}
