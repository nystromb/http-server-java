package http.configuration;

public class Settings {
    public static String PUBLIC_DIR =  System.getProperty("user.dir") + "/public/";
    public static int PORT = 5000;

    public static void parse(String[] args) {
        for (int option = 0; option < args.length; option++) {

            switch (args[option]) {
                case "-p":
                    PUBLIC_DIR = args[++option];
                    break;
                case "-d":
                    PORT = Integer.parseInt(args[++option]);
                    break;
            }
        }
    }

    public static void setUpLogger(){
        LoggerConfig.setUpLogger();
    }

    public static void createRoutes() {
        RouterConfig.setUpRoutes();
    }
}
