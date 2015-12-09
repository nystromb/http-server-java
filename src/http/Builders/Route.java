package http.Builders;

import http.Handlers.AuthHandler;
import http.Handlers.HttpExchange;
import http.Handlers.RedirectHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by nystrom on 12/6/15.
 */
public class Route {
    private boolean isAuthenticated = false;
    public List<HttpExchange> handlers = new ArrayList<>();
    public HttpExchange handler;
    private Observable model;

    public Route() {

    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthentication(String user, String password, String challenge) {
        handlers.add(new AuthHandler(user, password, challenge));
        isAuthenticated = true;
    }

    public void setNext(HttpExchange next) {
        handlers.add(next);
    }

    public void setHandler(HttpExchange controller) {
        this.handler = controller;
    }

    public void setRedirect(String redirectPath) {
        handlers.add(new RedirectHandler(redirectPath));
    }

    public void setModel(Observable model) {
        this.model = model;
    }
}
