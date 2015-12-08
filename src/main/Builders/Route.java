package main.Builders;

import main.Handlers.AuthHandler;
import main.Handlers.DirectoryHandler;
import main.Handlers.Resource;
import main.HttpExchange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nystrom on 12/6/15.
 */
public class Route {
    private boolean isAuthenticated = false;
    public List<HttpExchange> handlers = new ArrayList<>();
    public HttpExchange handler;

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
}
