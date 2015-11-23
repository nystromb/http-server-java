package main.Handlers;

import main.Request;

/**
 * Created by nystrom on 11/16/15.
 */
public class Route implements RequestHandler {
    static String data = "";

    public byte[] handle(Request request) {
        StringBuffer response = new StringBuffer();

        response.append(request.getVersion());
        response.append(" ");
        response.append("200 OK");
        response.append("\r\n");

        switch (request.getMethod()) {
            case "GET":
                response.append("\r\n");
                response.append(data);
                break;
            case "PUT":
                data = request.getBody();
                response.append("\r\n");
                break;
            case "POST":
                data = request.getBody();
                response.append("\r\n");
                break;
            case "DELETE":
                data = "";
                response.append("\r\n");
                break;
            case "OPTIONS":
                response.append("Allow: GET,HEAD,POST,OPTIONS,PUT");
                response.append("\r\n\r\n");
                break;
        }

        return response.toString().getBytes();
    }
}