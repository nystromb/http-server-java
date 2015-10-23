

public class Put implements Request {
	private String path = null;
	
	public Put(String path){
		this.path = path;
	}

	@Override
	public String getPath() {
		return this.path;
	}
}
