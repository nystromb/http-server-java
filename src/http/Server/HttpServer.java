package http.server;

import http.builders.Request;
import http.builders.RequestParser;
import http.builders.RequestReader;
import http.registry.Routes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer extends ServerSocket {
    private static Logger logger = Logger.getLogger( HttpServer.class.getName() );
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private Routes routes = new Routes();

    public HttpServer(int port) throws IOException {
        super(port);
    }

    public void start() throws IOException {
        while(true) {
            try {
                Socket client = accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String rawRequest = RequestReader.read(input);
                Request request = RequestParser.process(rawRequest);
                logger.log(Level.INFO, rawRequest);
                executorService.execute(new ServerRunner(client, request, routes));
            } catch (URISyntaxException e) {
                logger.log(Level.SEVERE, "Something went wrong");
            }
        }
    }
}

