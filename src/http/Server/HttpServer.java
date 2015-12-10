package http.Server;

import http.Builders.Request;
import http.Builders.RequestParser;
import http.Builders.RequestReader;
import http.Registry.Routes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nystrom on 12/1/15.
 */
public class HttpServer extends ServerSocket {
    ExecutorService executorService = Executors.newFixedThreadPool(15);
    Routes routes = new Routes();
    public HttpServer(int port) throws IOException {
        super(port);
    }

    public void setExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ExecutorService getExecutor() {
        return executorService;
    }

    public void start() throws IOException {
        while(true) {
            try {
                Socket client = accept();
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String rawRequest = RequestReader.read(input);
                Request request = RequestParser.process(rawRequest);
                executorService.execute(new ServerRunner(client, request, routes));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}

