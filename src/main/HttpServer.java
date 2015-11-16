package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class HttpServer {
	Socket client;

    public HttpServer(Socket client) {
		this.client = client;
    }

	public void run() throws IOException {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream());
        ){
            String rawRequest = RequestReader.read(input);
            System.out.print(rawRequest);
            Request request = RequestParser.process(rawRequest);
            RequestHandler response = Router.route(request);
            System.out.println(response);
            output.print(response);
            output.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
	}
}