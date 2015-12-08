package main.Registry;

import java.util.HashMap;

/**
 * Created by nystrom on 12/6/15.
 */
public class HttpResponseCodes extends HashMap<Integer, String> {
    public HttpResponseCodes(){
        this.put(200, "OK");
        this.put(204, "No Content");
        this.put(206, "Partial Content");
        this.put(302, "Found");
        this.put(405, "Method Not Allowed");
        this.put(404, "Not Found");
    }
}
