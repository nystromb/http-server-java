package test.configuration;


import http.router.Router;
import http.configuration.Settings;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Files;

import static http.configuration.Settings.PORT;
import static http.configuration.Settings.PUBLIC_DIR;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SettingsTest {
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testDefaultPortAndPublicDir() {
		assertEquals(5000, PORT);
        assertEquals(System.getProperty("user.dir") + "/public/", PUBLIC_DIR);
    }

    @Test
    public void testCreatesLogsDirectory(){
        Settings.setUpLogger();

        assertTrue(Files.exists(new File(PUBLIC_DIR, "/logs/").toPath()));
    }

    @Test
    public void testCreatesRoutes() throws URISyntaxException {
        Settings.createRoutes();

        assertTrue(Router.getRoute("/") != null);
    }
}