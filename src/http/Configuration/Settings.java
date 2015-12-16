package http.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Settings {
    private static final Logger logger = Logger.getLogger( "http.log" );
    public static int PORT = 5000;
    public static String PUBLIC_DIR =  System.getProperty("user.dir") + "/public/";

    public static void parse(String[] args) {
		for(int option = 0; option < args.length; option++){

            switch(args[option]){
                case "-p":
                    setPort(args[++option]);
                    break;
                case "-d":
                    setDirectory(args[++option]);
                    break;
            }
		}
	}

	private static void setDirectory(String directory) {
        PUBLIC_DIR = directory;
	}

	private static void setPort(String arg) {
		PORT = Integer.parseInt(arg);
	}

	public static int getPort() {
		return PORT;
	}

	public static String getRootDirectory() {
		return PUBLIC_DIR;
	}

    public static void setUpLogger(){
        try {
            File logs = new File(PUBLIC_DIR, "/logs/");
            if(Files.notExists(logs.toPath())){
                logs.mkdir();
            }
            FileHandler fileHandler = new FileHandler(new File(logs.toString(), "logfile.txt").toString(), true);
            logger.addHandler(fileHandler);
        }catch(IOException e){
            e.printStackTrace();
            logger.log(Level.SEVERE, "Couldn't set up logging");
        }
    }
}
