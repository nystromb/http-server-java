package main;

import java.io.*;
import java.net.Socket;

class HttpServer{
	Socket client;
    BufferedReader input;
    PrintWriter output;

    public HttpServer(Socket client) {
		this.client = client;
	}

	public void run() throws IOException{

        String request = "";
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter out = new PrintWriter(client.getOutputStream());
        while(in.ready()){
            request += (char) in.read();
        }
        System.out.print(request);
        Request req = RequestFactory.build(request);
        String response = ResponseFactory.getResponse(req);
        out.print(response);;
        out.flush();
        in.close();
        out.close();
	}
}