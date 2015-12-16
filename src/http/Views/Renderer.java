package http.views;

import http.builders.Request;
import main.Models.GameModel;

public interface Renderer {
    String render(Request request, GameModel board);
}
