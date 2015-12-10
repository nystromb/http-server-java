package test.Configuration;


import http.Configuration.Settings;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}