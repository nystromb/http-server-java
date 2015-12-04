package main.Handlers;

import main.Request;
import main.Response;

/**
 * Created by nystrom on 11/16/15.
 */
public class Resource implements Requestable {
    static String data = "";
    Response response = new Response();
    public Response getResponse(Request request) {
        response.setStatus("200 OK");

        switch (request.getMethod()) {
            case "GET":
                response.setBody(data.getBytes());
                break;
            case "PUT":
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

        return response;
    }
}