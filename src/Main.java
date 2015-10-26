import java.io.IOException;
import java.net.ServerSocket;

public class Main {	
	 public static void main(String[] args) {
		ServerSettings.parse(args);
		
		try (ServerSocket server = new ServerSocket(ServerSettings.getPort())) {
			while(true){
				new RequestHandler(server.accept()).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
