import java.net.Socket;

import org.junit.Before;


public class RequestHandlerTest {
	HttpServerThread thread;

	@Before
	public void setUp() throws Exception {
		thread = new HttpServerThread(new Socket());
	}
}
