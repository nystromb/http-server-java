package http.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerConfig {
    private static final Logger logger = Logger.getLogger( "http.log" );

    public static void setUpLogger(){
        try {
            File logs = new File(Settings.PUBLIC_DIR, "/logs/");
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
