package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;

import java.io.IOException;

/**
 * Created by nystrom on 12/9/15.
 */
public interface Router {
    Response handle(Request request) throws IOException;
}
