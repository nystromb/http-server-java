

public class Post implements Request {
	private String path = null;
	
	public Post(String path){
		this.path = path;
	}

	@Override
	public String getPath() {
		return this.path;
	}
}
