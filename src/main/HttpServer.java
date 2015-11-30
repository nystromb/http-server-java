package main;

import main.Handlers.*;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.logging.Level;

class HttpServer implements Runnable {
	Socket client;

    public HttpServer(Socket client) {
		this.client = client;
    }

	public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream output = client.getOutputStream()
        ){

            String rawRequest = RequestReader.read(input);
            Request request = RequestParser.process(rawRequest);

            Main.logger.log(Level.INFO, rawRequest);

            RequestHandler handler = Router.route(request);
            byte[] response = handler.handle(request);

            System.out.println(new String(response, "UTF-8"));

            output.write(response);
            output.flush();

        } catch (IOException e){
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}