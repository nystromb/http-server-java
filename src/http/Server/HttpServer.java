package http.Server;

import http.Registry.Routes;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nystrom on 12/1/15.
 */
public class HttpServer extends ServerSocket {
    ExecutorService executorService = Executors.newFixedThreadPool(15);

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
            executorService.execute(new ServerRunner(accept(), new Routes()));
        }
    }
}

