package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Router.AbstractRouter;
import main.Models.GameModel;
import test.Renderer;

import java.io.IOException;

/**
 * Created by nystrom on 12/8/15.
 */
public class TicTacToeHandler extends AbstractRouter {
    GameModel model;
    Renderer renderer;

    public TicTacToeHandler(GameModel model, Renderer renderer) {
        this.model = model;
        this.renderer = renderer;
    }

    @Override
    public Response handle(Request request) throws IOException {
        if(request.getMethod().equals("GET")){
            if(request.getQuery().length() > 0){
                String[] params = request.getQuery().split(" ");
                int move = Integer.parseInt(params[params.length-1]);
                model.play(move);
            }
            String boardHtml = renderer.render(model.board);
            return new Response.Builder(200, boardHtml).build();
        }

        return new Response.Builder(200, "Good stuff").build();
    }
}