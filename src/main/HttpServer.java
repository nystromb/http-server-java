package main;

import main.Handlers.*;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

class HttpServer {
	Socket client;
    Logger logger;
    public HttpServer(Socket client, Logger logger) {
		this.client = client;
        this.logger = logger;
    }

	public void run() throws IOException, URISyntaxException {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream output = client.getOutputStream()
        ){
            String rawRequest = RequestReader.read(input);
            logger.log(Level.INFO, rawRequest);
            Request request = RequestParser.process(rawRequest);
            RequestHandler handler = Router.route(request);
            byte[] response = handler.handle(request);
            System.out.println(new String(response, "UTF-8"));
            output.write(response);
            output.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
	}
}