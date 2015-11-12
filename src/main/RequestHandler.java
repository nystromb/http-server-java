package main;

import java.io.IOException;
import java.io.File;

public class RequestHandler {
    static String data = "";
    static File path;
    public static String getResponse(Request request) throws IOException {
        StringBuffer response = new StringBuffer();
        response.append(request.getVersion());
        response.append(" ");

        path = new File(ServerSettings.getDirectory(), request.getPath());

        if (Router.hasPath(request.getPath()) || path.exists()) {
            response.append("200 OK");
            response.append("\r\n");
        } else if(request.getPath().equals("/redirect")){
            response.append("302 Found");
            response.append("\r\n");
            response.append("Location: " + "http://" + request.getHeader("Host") + "/");
            response.append("\r\n");
        } else {
            response.append("404 Not Found");
            response.append("\r\n");
            return response.toString();
        }

        switch (request.getMethod()) {
            case "GET":
                String fileContents = "";
                if(path.isDirectory()) fileContents = ServerReader.readDirectoryContents(path);
                if(path.isFile()) fileContents = ServerReader.readFileContents(path);
                String responseBody = (data + fileContents);
                response.append("Content-Length: " + responseBody.getBytes().length + "\r\n");
                response.append("\r\n");
                response.append(responseBody);
                break;
            case "PUT":
                data = request.getBody();
                break;
            case "POST":
                data = request.getBody();
                break;
            case "DELETE":
                data = "";
                break;
            case "OPTIONS":
                response.append("Allow: GET,HEAD,POST,OPTIONS,PUT");
                response.append("\r\n\r\n");
                break;
        }

        return response.toString();
    }


}
