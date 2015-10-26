

public class Request {
	String method;
	String path;
	String httpVersion;
	
	public Request(String method, String path, String httpVersion) {
		this.method = method;
		this.path = path;
		this.httpVersion = httpVersion;
	}

	public String getRequestMethod() {
		return method;
	}

	public String getPath() {
		return path;
	}

	public String getProtocolVersion() {
		return httpVersion;
	}

}
