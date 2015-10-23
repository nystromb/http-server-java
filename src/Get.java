

public class Get implements Request {
	private String path = null;

	public Get(String path){
		this.path = path;
	}
	
	public String getPath(){
		return this.path;
	}
}
