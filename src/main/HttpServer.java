package main;

import main.Handlers.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class HttpServer {
	Socket client;

    public HttpServer(Socket client) {
		this.client = client;
        Router.addRoute("/", new DirectoryReader());
        Router.addRoute("/file1", new FileContentReader());
        Router.addRoute("/redirect", new RedirectRoute());
        Router.addRoute("/form", new Route());
        Router.addRoute("/method_options", new Route());
    }

	public void run() throws IOException {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream())
        ){
            String rawRequest = RequestReader.read(input);
            System.out.print(rawRequest);
            Request request = RequestParser.process(rawRequest);
            RequestHandler handler = Router.route(request);
            String response = handler.handle(request);
            System.out.println(response);
            output.print(response);
            output.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
	}
}