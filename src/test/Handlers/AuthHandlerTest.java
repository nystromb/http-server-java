package test.Handlers;

import http.Configuration.Settings;
import org.junit.Before;

/**
 * Created by nystrom on 11/24/15.
 */
public class AuthHandlerTest {

    @Before
    public void setUp() {
        Settings.parse(new String[]{"-d", "/Users/nystrom/Documents/cob_spec/public/"});
    }
//
//    @Test
//    public void testWithoutAuthorization() throws URISyntaxException, IOException {
//        AuthHandler handler = new AuthHandler("admin:hunter2");
//        Request request = new Request("GET", new URI("/logs"), "HTTP/1.1");
//
//        Response response = handler.handle(request);
//
//        assertTrue(response.statusLine.contains("401"));
//        assertTrue(response.headers.containsKey("WWW-Authenticate"));
//    }
//
//    @Test
//    public void testWithAuthorization() throws URISyntaxException, IOException {
//        AuthHandler handler = new AuthHandler("admin:hunter2");
//        Request request = new Request("GET", new URI("/some_uri"), "HTTP/1.1");
//
//        request.addHeader("Authorization", "Basic YWRtaW46aHVudGVyMg==");
//
//        Response response = handler.handle(request);
//
//        assertTrue(response.statusLine.contains("200"));
//    }
}
