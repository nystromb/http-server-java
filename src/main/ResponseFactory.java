package main;

import java.util.HashMap;

public class ResponseFactory {
    private static HashMap<String, String> pathContent = new HashMap<>();

	public static String getResponse(Request request) {
		String response = request.getProtocolVersion();

		response += " 200 OK\r\n";

        switch(request.getMethod()){
            case "GET":
                response += "Content-Length: " + getContent(request.getPath()).getBytes().length + "\r\n\r\n";
                response += getContent(request.getPath());
                break;
            case "PUT":
                pathContent.put(request.getPath(), request.getBody());
                break;
            case "POST":
                pathContent.put(request.getPath(), request.getBody());
                break;
            case "DELETE":
                pathContent.put(request.getPath(), null);
                break;
            case "OPTIONS":
                response += "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n\r\n";
                break;
        }

		return response;
	}

    public static String getContent(String path){
        String value = pathContent.get(path);
        if(value == null){
            return "";
        }else {
            return value;
        }
    }
}
