package http.response;

import java.util.HashMap;

public class ResponseCodes extends HashMap<Integer, String> {
    public ResponseCodes(){
        this.put(200, "OK");
        this.put(204, "No Content");
        this.put(206, "Partial Content");
        this.put(302, "Found");
        this.put(401, "Unauthorized");
        this.put(405, "Method Not Allowed");
        this.put(404, "Not Found");
    }
}
