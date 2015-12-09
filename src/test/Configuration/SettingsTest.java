package test.Configuration;


import http.Configuration.ServerSettings;
import http.Configuration.Settings;
import http.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import static org.junit.Assert.assertTrue;

public class SettingsTest {
	@Before
	public void setUp() throws Exception {
		String[] args = new String[4];
		
		args[0] = "-p";
		args[1] = "5000";
		args[2] = "-d";
		args[3] = "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/";

		Settings.parse(args);
	}

	@Test
	public void testIfSetsPort() {
		Assert.assertEquals(5000, Settings.getPort());
	}
	
	@Test
	public void testIfSetsFileDirectory(){
		Assert.assertEquals("/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/", Settings.getRootDirectory());
	}

//    @Test
//    public void testIfBuildRoutes() throws URISyntaxException, IOException {
//        Main.buildRoutes();
//        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");
//        Route handler = Router.buildRoute(request);
//        assertTrue(handler instanceof AuthHandler);
//    }

    @Test
    public void testSetUpLogger() throws IOException{
        Main.setUpLogger();
        assertTrue(Main.logger instanceof Logger);
        assertTrue(Main.logger.getHandlers()[0] instanceof FileHandler);
    }

}