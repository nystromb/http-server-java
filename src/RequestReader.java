

import java.io.BufferedReader;
import java.io.IOException;

public class RequestReader {
	public static String read(BufferedReader input) throws IOException {
		StringBuilder request = new StringBuilder();
		
		while(input.ready()){
			request.append((char)input.read());
		}
		
		return request.toString();
	}

}
