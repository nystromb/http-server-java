package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;

import java.io.IOException;
import java.util.Observable;

/**
 * Created by nystrom on 12/8/15.
 */
public class TicTacToeHandler implements Handler {
    Observable model;

    public TicTacToeHandler(Observable model) {
        this.model = model;
    }

    @Override
    public Response handle(Request request) throws IOException {
        return new Response.Builder(200, "Good stuff").build();
    }
}
