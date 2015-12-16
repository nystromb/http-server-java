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

        assertTrue(Files.exists(new File(System.getProperty("user.dir"), "/logs/").toPath()));
    }

    @Test
    public void testCreatesRoutes() throws URISyntaxException {
        Settings.createRoutes();

        assertTrue(Router.getRoute("/") != null);
        assertTrue(Router.getRoute("/file1") != null);
        assertTrue(Router.getRoute("/file2") != null);
        assertTrue(Router.getRoute("/text-file.txt") != null);
        assertTrue(Router.getRoute("/patch-content.txt") != null);
        assertTrue(Router.getRoute("/image.png") != null);
        assertTrue(Router.getRoute("/image.gif") != null);
        assertTrue(Router.getRoute("/partial_content.txt") != null);
        assertTrue(Router.getRoute("/image.jpeg") != null);
        assertTrue(Router.getRoute("/someDirectory") != null);
    }
}