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
            Request req = RequestFactory.build(request);
            String response = ResponseFactory.getResponse(req);

            output.print(response);
            output.flush();
        } catch (IOException e){
            e.printStackTrace();
        }

	}
}