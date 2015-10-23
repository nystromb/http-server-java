

public class Option implements Request {
	private String path = null;
	
	public Option(String path){
		this.path = path;
	}

	@Override
	public String getPath() {
		return this.path;
	}
}
