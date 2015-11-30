package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.logging.Logger;

public class Main {
    public static Logger logger = Logger.getLogger("ServerLogger");

	 public static void main(String[] args) throws URISyntaxException, IOException {
         ServerSettings.parse(args);
         ServerSettings.setUpLogger();
         ServerSettings.buildRoutes();

         try (ServerSocket serverSocket = new ServerSocket(ServerSettings.getPort())) {

             while(true) {
                 Socket client = serverSocket.accept();
                 new HttpServer(client).run();
             }

         } catch (IOException e) {
             System.out.println("Exception caught when trying to listen on port " + ServerSettings.getPort() + " or listening for a connection");
             System.out.println(e.getMessage());
         }
     }
}
