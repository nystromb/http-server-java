import java.util.HashMap;

public class Request {
	String method;
	String path;
	String httpVersion;
	String body = null;
	HashMap<String, String> headers = new HashMap<String, String>();
	
	public Request(String method, String path, String httpVersion) {
		this.method = method;
		this.path = path;
		this.httpVersion = httpVersion;
	}

	public String getMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public String getProtocolVersion() {
		return httpVersion;
	}
}
