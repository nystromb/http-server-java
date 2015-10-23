import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpServer {
	Socket client;
	BufferedReader input;
	PrintWriter output;
	
	public  HttpServer(Socket client) throws IOException {
		this.client = client;
		input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		output = new PrintWriter(client.getOutputStream());
	}

	public void run() throws IOException {
		String request = RequestReader.read(input);
		output.write("HTTP/1.1" + " " + "200 OK" + "\r\n\r\n" + request);
		output.close();
	}
}
