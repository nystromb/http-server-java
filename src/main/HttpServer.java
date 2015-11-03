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

	public void run() throws IOException {
        String request = "";

        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        output = new PrintWriter(client.getOutputStream());

        request += input.readLine() + "\r\n";
        while(input.ready()) {
            request += (char) input.read();
        }

        System.out.println(request);

        Request req = RequestFactory.build(request);
        String response = ResponseFactory.getResponse(req);

        output.print(response);;
        output.flush();
        input.close();
        output.close();
	}
}