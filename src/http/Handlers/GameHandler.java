package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Router.AbstractRouter;
import http.Views.Renderer;
import main.Models.GameModel;
import main.Players.RandomPlayer;
import main.Players.UnbeatablePlayer;

import java.io.IOException;

/**
 * Created by nystrom on 12/8/15.
 */
public class GameHandler extends AbstractRouter {
    GameModel model;
    Renderer renderer;

    public GameHandler(GameModel model, Renderer renderer) {
        this.model = model;
        this.renderer = renderer;
    }

    @Override
    public Response handle(Request request) throws IOException {
        if(request.getMethod().equals("GET")){
            if(request.getQuery().length() > 0){
                String[] params = request.getQuery().split(" ");

                if(params[0].startsWith("move")){
                    int move = Integer.parseInt(params[params.length - 1]);
                    if(!model.isOver()) {
                        model.play(move);
                        if(currentPlayerComptuer()){
                            move = model.currentPlayer.getMove(model);
                            model.play(move);
                        }
                    }
                }else if(params[0].startsWith("clear")){
                    model.board.clearAll();
                }
            }

            String boardHtml = renderer.render(request, model);
            return new Response.Builder(200, boardHtml).build();
        }

        return new Response.Builder(200, "Good stuff").build();
    }

    private boolean currentPlayerComptuer() {
        return (model.currentPlayer instanceof UnbeatablePlayer) || (model.currentPlayer instanceof RandomPlayer);
    }
}