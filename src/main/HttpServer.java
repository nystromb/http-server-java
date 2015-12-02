package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by nystrom on 12/1/15.
 */
public class HttpServer extends ServerSocket {
    ExecutorService executorService;

    public HttpServer(int port) throws IOException {
        super(port);
        executorService = Executors.newFixedThreadPool(15);
    }

    public void setExecutor(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ExecutorService getExecutor() {
        return executorService;
    }

    public void start() throws IOException {
        while(true) executorService.execute(new HttpProtocolHandler(accept()));
    }
}
