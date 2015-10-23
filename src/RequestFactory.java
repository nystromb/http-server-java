

public class RequestFactory {
	public static Request build(String requestMessage) {
		String [] reqTokens = requestMessage.split(" ");
		
		if(reqTokens[0].equals("GET"))
			return new Get(reqTokens[1]);
		else if(reqTokens[0].equals("POST"))
			return new Post(reqTokens[1]);
		else if (reqTokens[0].equals("PUT"))
			return new Put(reqTokens[1]);
		else if (reqTokens[0].equals("OPTION"))
			return new Option(reqTokens[1]);
		
		return null;
	} 
}
