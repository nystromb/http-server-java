package main;

import java.net.URI;
import java.util.Hashtable;

public class Request {
    public URI uri;
    String method, version, body = "";
    Hashtable<String, String> headers = new Hashtable<>();

    public Request(String method, URI uri, String protocol){
        this.method = method;
        this.uri = uri;
        this.version = protocol;
    }

	public String getMethod() {
		return method;
	}

    public String getVersion(){
        return version;
    }

    public String getPath() {
        return uri.getPath();
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

	public boolean hasHeader(String headerTitle) {
		return headers.containsKey(headerTitle);
	}

    public String getQuery() {
        return uri.getQuery();
    }
}
