package main;

public class RequestParser {
	public static Request build(String requestMessage) {
		Request request = new Request();

		String[] requestTokens = requestMessage.split("\r\n");

		String [] requestLine = requestTokens[0].split(" ");
		request.setMethod(requestLine[0]);
        request.setPath(requestLine[1]);
		request.setProtocolVersion(requestLine[2]);
		
		int line = 1;
		if(requestTokens.length > 1){
			while(line < requestTokens.length && !requestTokens[line].isEmpty()){
				String[] headerLine = requestTokens[line].split(": ");
				request.addHeader(headerLine[0], headerLine[1]);
				line++;
			}
		}

		if(line < requestTokens.length){
			request.body = requestTokens[++line];
		}
		
		return request;
	} 
}
