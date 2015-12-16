package http.server;

import http.builders.Request;
import http.builders.Response;
import http.builders.Route;
import http.registry.Routes;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerRunner implements Runnable {
    private static Logger logger = Logger.getLogger( ServerRunner.class.getName() );
    private Routes routes;
    private Socket client;
    private Request request;
    private Response response;

    public ServerRunner(Socket client, Request request, Routes routes) {
        this.client = client;
        this.request = request;
        this.routes = routes;
    }

    @Override
    public void run() {
        try (
                OutputStream output = client.getOutputStream()
        ){
            if(routes.containsKey(request.getPath())){
                Route route = routes.get(request.getPath());
                response = route.handler.handle(request);
            }else{
                response = new Response.Builder(404, "Not Found").build();
            }
            logger.log(Level.INFO, response.statusLine);
            output.write(response.toByteArray());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Something went wrong");
        }
    }
}
