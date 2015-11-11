package main;

import java.util.Map.Entry;
import java.io.File;

public class RequestHandler {
    static String data = "";

    public static String getResponse(Request request) {
        Response response = new Response();

        response.setProtocolVersion(request.getProtocolVersion());

        if (Router.hasPath(request.getPath())) {
            response.setStatusCode("200 OK");
        } else if(request.getPath().equals("/redirect")){
            response.setStatusCode("302 Found");
            response.addHeader("Location", "http://localhost:5000/");
        } else {
            response.setStatusCode("404 Not Found");
        }

        switch (request.getMethod()) {
            case "GET":
                File path = new File(ServerSettings.getDirectory() + request.getPath());
                String fileContents = ServerReader.getContent(path);
                response.setBody(data + "<!DOCTYPE html><html><head></head><body>" + fileContents + "</body></html>");
                response.addHeader("Content-Length", String.valueOf(response.getBody().getBytes().length));
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
                response.addHeader("Allow", "GET,HEAD,POST,OPTIONS,PUT");
                break;
        }

        return response.toString();
    }

    private static class Response extends Request{

        private String statusCode;

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String toString(){
            StringBuffer response = new StringBuffer();

            response.append(version + " " + statusCode);

            for(Entry<String, String> header : headers.entrySet()){
                response.append("\r\n");
                response.append(header.getKey() + ": " + header.getValue());
            }
            response.append("\r\n\r\n");
            response.append(body);

            return response.toString();
        }
    }
}
