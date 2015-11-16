package main;

import java.util.Hashtable;

public class Request {
    String method, path, version, body = "";
    Hashtable<String, String> headers = new Hashtable<>();

	public Request(String [] startLine) {
        this.method = startLine[0];
        this.path = startLine[1];
        this.version = startLine[2];
    }

	public String getMethod() {
		return method;
	}

    public String getVersion(){
        return version;
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

	public String getBody() { return body; }

	public void setBody(String body) { this.body = body; }

	public boolean hasHeader(String headerTitle) {
		return headers.containsKey(headerTitle);
	}
}
