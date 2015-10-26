import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RequestHandler extends Thread {
	Client client;
	String request;
	
	public RequestHandler(Socket socket) throws IOException { 
		client = new Client(socket);
		client.setInput(new BufferedReader(new InputStreamReader(socket.getInputStream())));
		client.setOutput(new PrintWriter(socket.getOutputStream()));
	}

	public void run() {
			try {
				request = client.getRequest();
				//Request req = RequestFactory.build(request);
				String response = "HTTP/1.1 200 OK\r\nAllow: GET,HEAD,POST,OPTIONS,PUT\r\n\r\n" + request;
				client.send(response);
			} catch (IOException e) {
				e.printStackTrace();
			}
    }		
}

