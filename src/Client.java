import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	Socket socket;
	BufferedReader input;
	PrintWriter output;
	
	public Client(Socket socket) throws IOException {
		this.socket = socket;
	}
	
	public void setInput(BufferedReader input) {
		this.input = input;
	}
	
	public void setOutput(PrintWriter output) {
		this.output = output;
	}
	
	public String getRequest() throws IOException {
		StringBuilder request = new StringBuilder();

		while(input.ready()) {
		    request.append((char) input.read());
		}
		
		return request.toString();
	}

	public void send(String response) {
		this.output.println(response);
		this.output.close();
	}
}
