package http.Configuration;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Settings {
    private static final Logger logger = Logger.getLogger( Settings.class.getName() );
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
        setUpLogger();
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

    public static void setUpLogger(){
        try {
            FileHandler fileHandler = new FileHandler("logs/logfile.txt", true);
            logger.addHandler(fileHandler);
        }catch(IOException e){
            logger.log(Level.SEVERE, "Couldn't set up logging");
        }
    }
}
