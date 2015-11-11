package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class HttpServer{
	Socket client;

    public HttpServer(Socket client) {
		this.client = client;
        Router.addResource("/");
        Router.addResource("/form");
        Router.addResource("/method_options");
    }

	public void run() throws IOException {
        String line, request = "";

        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream());
        ){
            while((line = input.readLine()) != null){
                request += line + "\r\n";

                if(line.isEmpty()) {
                    while(input.ready()){
                        request += (char) input.read();
                    }
                    break;
                }
            }

            System.out.print(request);

            Request req = RequestParser.build(request);
            String response = RequestHandler.getResponse(req);
            System.out.println(response);
            output.print(response);
            output.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
	}
}