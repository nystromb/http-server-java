package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger("ServerLogger");

	 public static void main(String[] args) throws URISyntaxException, IOException {
         ServerSettings.parse(args);
         ServerSettings.setUpLogger();
         ServerSettings.buildRoutes();

         try (ServerSocket serverSocket = new ServerSocket(ServerSettings.getPort())) {
             logger.log(Level.INFO, "Server starting on port " + ServerSettings.getPort());

             ExecutorService pool = Executors.newFixedThreadPool(15);
             while(true) {
                 Socket client = serverSocket.accept();
                 pool.execute(new HttpServer(client));
             }
         } catch (IOException e) {
             logger.log(Level.SEVERE, "Could not start server on port " + ServerSettings.getPort());
         }
     }
}
