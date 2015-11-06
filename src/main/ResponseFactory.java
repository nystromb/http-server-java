package main;

import java.util.HashMap;

public class ResponseFactory {
    private static HashMap<String, String> pathContent = new HashMap<>();

	public static String getResponse(Request request) {
        StringBuffer response = new StringBuffer();
        response.append(request.getProtocolVersion());
		response.append(" ");

        if(Router.hasPath(request.getPath())){
            response.append("200 OK\r\n");
        }else{
            response.append("404 Not Found\r\n");
        }

        switch(request.getMethod()){
            case "GET":
                response.append("Content-Length: " + getContent(request.getPath()).getBytes().length + "\r\n\r\n");
                response.append(getContent(request.getPath()));
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
                response.append("Allow: GET,HEAD,POST,OPTIONS,PUT\r\n\r\n");
                break;
        }

		return response.toString();
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
