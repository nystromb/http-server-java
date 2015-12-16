package http.server;

import http.router.Router;
import http.request.Request;
import http.response.Response;
import http.router.Route;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ServerRunner implements Runnable {
    private Socket client;
    private Request request;
    private Router router;
    private Response response;

    public ServerRunner(Socket client, Request request) {
        this.client = client;
        this.request = request;
        this.router = new Router();
    }

    @Override
    public void run() {
        try (OutputStream output = client.getOutputStream()){
            Route route = router.getRoute(request.getPath());
            if(route != null){
                response = route.handle(request);
            }else{
                response = new Response.Builder(404, "Not Found").build();
            }
            output.write(response.toByteArray());
        } catch (IOException e) {

        }
    }
}
