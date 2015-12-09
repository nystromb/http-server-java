package http.Configuration;

public class Settings {
	private static int port = 5000;
	private static String directory;



    public static void parse(String[] args) {
		for(int option = 0; option < args.length; option++){
			if(args[option].equals("-p")){
				setPort(args[++option]);
			}else if (args[option].equals("-d")){
				setDirectory(args[++option]);
			}
		}
	}

	private static void setDirectory(String directory) {
		Settings.directory = directory;
	}

	private static void setPort(String arg) {
		port = Integer.parseInt(arg);
	}

	public static int getPort() {
		return port;
	}

	public static String getRootDirectory() {
		return directory;
	}
}
