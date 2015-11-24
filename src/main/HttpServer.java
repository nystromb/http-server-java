package main;

import main.Handlers.*;

import java.io.*;
import java.net.Socket;
import java.net.URISyntaxException;

class HttpServer {
	Socket client;

    public HttpServer(Socket client) {
		this.client = client;
        Router.addRoute("/", new DirectoryReader());
        Router.addRoute("/file1", new FileContentReader());
        Router.addRoute("/file2", new FileContentReader());
        Router.addRoute("/redirect", new RedirectRoute());
        Router.addRoute("/form", new Route());
        Router.addRoute("/image.jpeg", new ImageFileReader());
        Router.addRoute("/image.png", new ImageFileReader());
        Router.addRoute("/image.gif", new ImageFileReader());
        Router.addRoute("/text-file.txt", new FileContentReader());
        Router.addRoute("/method_options", new Route());
    }

	public void run() throws IOException, URISyntaxException {
        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                OutputStream output = client.getOutputStream()
        ){
            String rawRequest = RequestReader.read(input);
            System.out.print(rawRequest);
            Request request = RequestParser.process(rawRequest);
            RequestHandler handler = Router.route(request);
            byte[] response = handler.handle(request);
            System.out.println(response);
            output.write(response);
            output.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
	}
}