package http.request;

import java.net.URI;
import java.net.URISyntaxException;

public class RequestParser {
    static Request request;

    public static Request process(String rawRequest) throws URISyntaxException {
        for(String line : parseRequestLines(rawRequest)){
            if(lineIsStartOfRequest(line)){
                String[] startLine = parseStartLine(line);
                request = new Request(startLine[0], new URI(startLine[1]), startLine[2]);
            }else if(lineIsAHeader(line)){
                String [] heading = parseHeader(line);
                request.addHeader(heading[0], heading[1]);
            }else{
                request.setBody(line);
            }
        }
        return request;
    }

    private static String[] parseRequestLines(String rawRequest) {
        return rawRequest.split("\r\n");
    }

    private static boolean lineIsStartOfRequest(String line) {
        return (line.split(" ").length == 3 && line.contains("HTTP/1.1"));
    }

    private static String[] parseStartLine(String line){
        return line.split(" ");
    }

    private static boolean lineIsAHeader(String line) {
        return (parseHeader(line).length == 2);
    }

    private static String[] parseHeader(String line) {
        return line.split(": ");
    }
}
