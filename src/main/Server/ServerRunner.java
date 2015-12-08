package main.Server;

import main.Builders.*;
import main.Router.Router;
import main.Main;
import main.Registry.Routes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.logging.Level;

/**
 * Created by nystrom on 12/1/15.
 */
public class ServerRunner implements Runnable {
    Routes routes = new Routes();
    Socket client;

    public ServerRunner(Socket client, Routes routes) {
        this.client = client;
        this.routes = routes;
    }

    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream output = client.getOutputStream()
        ){

            String rawRequest = RequestReader.read(input);
            Main.logger.log(Level.INFO, "Received Request from " + client.getRemoteSocketAddress().toString() + "\n" + rawRequest);

            Request request = RequestParser.process(rawRequest);
            Route route = Router.buildRoute(request);

            Response response = Router.route(route, request);
            Main.logger.log(Level.INFO, "Response\n" + new String(response.toByteArray()));

            output.write(response.toByteArray());
        }catch(IOException e){
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
