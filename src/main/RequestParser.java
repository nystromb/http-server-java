package main;

public class RequestParser {
    static Request request;

    public static Request process(String rawRequest) {
        for(String line : parseRequestLines(rawRequest)){
            if(lineIsStartOfRequest(line)){
                request = new Request(parseStartLine(line));
            }else if(lineIsAHeader(line)){
                String [] heading = parseHeader(line);
                request.addHeader(heading[0], heading[1]);
            }else{
                if(request.hasHeader("Content-Length")){
                    request.setBody(line);
                }
            }
        }
        return request;
    }

    private static String[] parseRequestLines(String rawRequest) {
        return rawRequest.split("\r\n");
    }

    private static boolean lineIsStartOfRequest(String line) {
        return (line.split(" ").length == 3);
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
