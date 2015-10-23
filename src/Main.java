import java.io.IOException;
import java.net.ServerSocket;


public class Main {
	public static final String CRLF = "\r\n\r\n";
	
	 public static void main(String[] args) {
		ServerSettings.parse(args);
		
		try (ServerSocket server = new ServerSocket(ServerSettings.getPort())) {
			while(true){
				HttpServer runner = new HttpServer(server.accept());
				runner.run();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
