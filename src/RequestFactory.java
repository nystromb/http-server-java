public class RequestFactory {
	private static Request request;
		
	public static Request build(String requestMessage) {
		String [] requestTokens = requestMessage.split("\r\n");
		
		String [] requestLine = requestTokens[0].split(" ");
		
		String method = requestLine[0];
		String path = requestLine[1];
		String version = requestLine[2];
		
		request = new Request(method, path, version);
		
		int line = 1;
		if(requestTokens.length > 1){
			while(line < requestTokens.length && !requestTokens[line].isEmpty()){
				String[] headerLine = requestTokens[line].split(": ");
				request.headers.put(headerLine[0], headerLine[1]);
				line++;
			}
		}
		
		if(line < requestTokens.length){
			request.body = requestTokens[++line];
		}
		
		return request;
	} 
}
