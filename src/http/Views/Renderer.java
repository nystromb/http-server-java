package http.Views;

import http.Builders.Request;
import main.Models.GameModel;

public interface Renderer {
    String render(Request request, GameModel board);
}
