package main;

public class ResponseFactory {

	public static String getResponse(Request request) {
		String response = "";

		response += request.getProtocolVersion() + " 200 OK\r\n";

		if(request.getMethod().equals("OPTIONS"))
			response += "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n";

        response += "\r\n";

		if(request.getBody() != null)
			response += request.getBody();


		return response;
	}
}
