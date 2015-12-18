package http.server;

import http.request.Request;
import http.request.RequestParser;
import http.request.RequestReader;

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

public class HttpServer {
    private static final Logger logger = Logger.getLogger( "http.log" );
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private ServerSocket server;
    private Socket client;

    public HttpServer(int port) throws IOException {
        server = new ServerSocket(port);
    }

    public void start() throws IOException {
        while(true) {
            try {
                client = server.accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String rawRequest = RequestReader.read(input);
                Request request = RequestParser.process(rawRequest);
                logger.log(Level.INFO, rawRequest);
                executorService.execute(new ServerRunner(client, request));
            } catch (URISyntaxException e) {
                logger.log(Level.SEVERE, "Something went wrong");
            }
        }
    }
}

