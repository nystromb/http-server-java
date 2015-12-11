package http.Server;

import http.Builders.Request;
import http.Builders.Response;
import http.Builders.Route;
import http.Registry.Routes;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by nystrom on 12/1/15.
 */
public class ServerRunner implements Runnable {
    private final Logger logger = Logger.getLogger( ServerRunner.class.getName() );
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
                response = route.handle(request);
            }else{
                response = new Response.Builder(404, "Not Found").build();
            }
            output.write(response.toByteArray());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
