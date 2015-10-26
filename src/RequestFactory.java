public class RequestFactory {
	static Request req;
	
	public static Request build(String requestMessage) {
		String [] requestLines = requestMessage.split("\r\n");
		
		String [] requestLine = requestLines[0].split(" ");
		
		String method = requestLine[0];
		String path = requestLine[1];
		String version = requestLine[2];
		
		return new Request(method, path, version);
	} 
}
