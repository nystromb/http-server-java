package test.Logger;

import com.sun.corba.se.spi.activation.Server;
import main.Request;
import main.ServerSettings;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import static org.junit.Assert.*;

/**
 * Created by nystrom on 11/25/15.
 */
public class LoggerTest {
    Logger logger;

    @Before
    public void setUp() throws IOException {
        logger = Logger.getLogger("ServerLogger");
        FileHandler fileHandler = new FileHandler(ServerSettings.getDirectory() + "logs", true);
        logger.addHandler(fileHandler);
    }

    @Test
    public void test() throws URISyntaxException, IOException {
        Request request = new Request("GET", new URI("/some_path"), "HTTP/1.1");

        logger.log(Level.INFO, "GET /these HTTP/1.1");

        assertTrue(new String(Files.readAllBytes(new File(ServerSettings.getDirectory() + "logs").toPath()), "UTF-8").contains("GET /these HTTP/1.1"));
    }
}
