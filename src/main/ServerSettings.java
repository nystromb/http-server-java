package main;


import main.Handlers.*;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ServerSettings {
	private static int port = 5000;
	private static String dir;
	
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
		dir = directory;
	}

	private static void setPort(String arg) {
		port = Integer.parseInt(arg);
	}

	public static int getPort() {
		return port;
	}

	public static String getDirectory() {
		return dir;
	}

    public static void buildRoutes() {
        Router.addRoute("/", new DirectoryReader());
        Router.addRoute("/file1", new FileContentReader());
        Router.addRoute("/file2", new FileContentReader());
        Router.addRoute("/redirect", new RedirectRoute());
        Router.addRoute("/form", new Route());
        Router.addRoute("/parameters", new ParameterDecoder());
        Router.addRoute("/image.jpeg", new ImageFileReader());
        Router.addRoute("/image.png", new ImageFileReader());
        Router.addRoute("/image.gif", new ImageFileReader());
        Router.addRoute("/partial_content.txt", new FileRangeReader());
        Router.addRoute("/text-file.txt", new FileContentReader());
        Router.addRoute("/method_options", new Route());
        Router.addRoute("/logs", new LogsHandler("admin:hunter2"));
        Router.addRoute("/patch-content.txt", new FileContentReader());
    }

    public static Logger getLogger() throws IOException{
        Logger logger = Logger.getLogger("ServerLogger");
        FileHandler fileHandler = new FileHandler(ServerSettings.getDirectory() + "/log/logs.txt", true);
        logger.addHandler(fileHandler);
        return logger;
    }
}
