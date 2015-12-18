package http.server;

import http.router.Router;
import http.request.Request;
import http.response.Response;
import http.router.Route;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerRunner implements Runnable {
    private static final Logger logger = Logger.getLogger("http.log");
    private Socket client;
    private Request request;
    private Response response;

    public ServerRunner(Socket client, Request request) {
        this.client = client;
        this.request = request;
    }

    @Override
    public void run() {
        try (OutputStream output = client.getOutputStream()){
            Route route = Router.getRoute(request.getPath());
            if(route != null){
                response = route.handle(request);
            }else{
                response = new Response.Builder(404, "Not Found").build();
            }
            logger.log(Level.INFO,  new String(response.toByteArray()));
            output.write(response.toByteArray());
        } catch (IOException e) {

        }
    }
}
