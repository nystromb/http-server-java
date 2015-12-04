package test;


import static org.junit.Assert.*;

import main.*;
import main.Handlers.LogsHandler;
import main.Handlers.Requestable;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class ServerSettingsTest {

	@Before
	public void setUp() throws Exception {
		String[] args = new String[4];
		
		args[0] = "-p";
		args[1] = "5000";
		args[2] = "-d";
		args[3] = "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/";
		
		ServerSettings.parse(args);
	}

	@Test
	public void testIfSetsPort() {
		Assert.assertEquals(5000, ServerSettings.getPort());
	}
	
	@Test
	public void testIfSetsFileDirectory(){
		Assert.assertEquals("/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/", ServerSettings.getRootDirectory());
	}

    @Test
    public void testIfBuildRoutes() throws URISyntaxException, IOException {
        Main.buildRoutes();
        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");
        Requestable handler = Router.getHandler(request);
        assertTrue(handler instanceof LogsHandler);
    }

    @Test
    public void testSetUpLogger() throws IOException{
        Main.setUpLogger();
        assertTrue(Main.logger instanceof Logger);
        assertTrue(Main.logger.getHandlers()[0] instanceof FileHandler);
    }

}