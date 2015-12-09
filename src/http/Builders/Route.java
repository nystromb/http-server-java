package http.Builders;

import http.Handlers.*;

/**
 * Created by nystrom on 12/8/15.
 */
public class Route {
    private Handler authentication, redirect, encoding;
    public Handler handler = new Resource();

    public Route() {

    }

    public Route authenticate(String user, String password, String secret) {
        authentication = new AuthHandler(user, password, secret);
        return this;
    }

    public void setRedirectTo(String redirect) {
        this.redirect = new RedirectHandler(redirect);
    }

    public void supportEncoding() {
        encoding = new ParameterHandler();
    }

    public boolean isAuthenticated() {
        return (authentication != null);
    }

    public boolean isRedirected() {
        return (redirect != null);
    }

    public boolean supportsEncoding() {
        return (encoding != null);
    }

    public void setMyHandler(Handler handler) {
        this.handler = handler;
    }

    public Handler getAuth() {
        return authentication;
    }
}
