package main;

import java.io.*;
import java.net.ServerSocket;

public class Main {
	 public static void main(String[] args) {
		ServerSettings.parse(args);
         try (ServerSocket serverSocket = new ServerSocket(ServerSettings.getPort())) {
             while(true)
                 new HttpServer(serverSocket.accept()).run();
         } catch (IOException e) {
             System.out.println("Exception caught when trying to listen on port " + ServerSettings.getPort() + " or listening for a connection");
             System.out.println(e.getMessage());
         }
     }
}
