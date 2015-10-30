import javax.xml.ws.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class HttpServer{
	Socket client;
	Request parsedRequest;

	public HttpServer(Socket client) {
		this.client = client;
	}

	public void run() {

		try(
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);
				BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		) {

			String line, rawRequest = "";
			while((line = input.readLine()) != null){
				rawRequest += line + "\r\n";

				if(!input.ready()) {
					parsedRequest = RequestFactory.build(rawRequest);
					break;
				}
			}

			String response = ResponseFactory.getResponse(parsedRequest);
			out.print(response);
			out.flush();

			System.out.print(rawRequest);
		}catch(IOException e){
			e.printStackTrace();
		}

	}
}