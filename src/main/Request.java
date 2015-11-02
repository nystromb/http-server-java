package main;

import java.util.HashMap;

public class Request {
	String method;
	String path;
	String version;
	String body = null;
	HashMap<String, String> headers = new HashMap<String, String>();

	public Request() {

	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
        this.method = method;
	}

    public void setProtocolVersion(String version){
        this.version = version;
    }

    public String getProtocolVersion(){
        return version;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

	public void addHeader(String headerTitle, String value) {
		headers.put(headerTitle, value);
	}

	public String getHeader(String key) {
		return headers.get(key);
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
