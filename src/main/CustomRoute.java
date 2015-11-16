package main;

/**
 * Created by nystrom on 11/16/15.
 */
public class CustomRoute implements RequestHandler {
    static String data = "";

    public String handle(Request request) {
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

//
//        path = new File(ServerSettings.getDirectory(), request.getPath());
//
//        if (Router.hasPath(request.getPath()) || path.exists()) {
//            response.append("200 OK");
//            response.append("\r\n");
//        } else if(request.getPath().equals("/redirect")){
//            response.append("302 Found");
//            response.append("\r\n");
//            response.append("Location: " + "http://" + request.getHeader("Host") + "/");
//            response.append("\r\n");
//        } else {
//            response.append("404 Not Found");
//            response.append("\r\n");
//            return response.toString();
//        }
//