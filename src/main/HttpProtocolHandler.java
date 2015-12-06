package main;

import main.Handlers.Requestable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URISyntaxException;
import java.util.logging.Level;

/**
 * Created by nystrom on 12/1/15.
 */
public class HttpProtocolHandler implements Runnable {
    Socket client;

    public HttpProtocolHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream output = client.getOutputStream()
        ){

            String rawRequest = RequestReader.read(input);
            Main.logger.log(Level.INFO, "Received Request from " + client.getRemoteSocketAddress().toString() + "\n" + rawRequest);

            Request request = RequestParser.process(rawRequest);
            Requestable handler = Router.getHandler(request);

            byte[] response = handler.getResponse(request);
            Main.logger.log(Level.INFO, "Response\n" + new String(response));

            output.write(response);
        }catch(IOException e){
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
