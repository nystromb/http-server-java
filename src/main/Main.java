package main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URISyntaxException;

public class Main {
	 public static void main(String[] args) throws URISyntaxException {
         ServerSettings.parse(args);
         ServerSettings.buildRoutes();
         try (ServerSocket serverSocket = new ServerSocket(ServerSettings.getPort())) {
             while(true) {
                 new HttpServer(serverSocket.accept(), ServerSettings.getLogger()).run();
             }
         } catch (IOException e) {
             System.out.println("Exception caught when trying to listen on port " + ServerSettings.getPort() + " or listening for a connection");
             System.out.println(e.getMessage());
         }
     }
}
