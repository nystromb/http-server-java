import java.net.Socket;

import org.junit.Before;


public class RequestHandlerTest {
	RequestHandler thread;

	@Before
	public void setUp() throws Exception {
		thread = new RequestHandler(new Socket());
	}
}
