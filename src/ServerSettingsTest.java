


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		assertEquals(5000, ServerSettings.getPort());
	}
	
	@Test
	public void testIfSetsFileDirectory(){
		assertEquals("/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/", ServerSettings.getDirectory());
	}
}