package http.Handlers;

import http.Builders.Request;
import http.Builders.Response;
import http.Configuration.Settings;
import http.Router.AbstractRouter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by nystrom on 12/9/15.
 */
public class LogsHandler extends AbstractRouter {

    public Response handle(Request request) throws IOException {
        byte[] logs = Files.readAllBytes(Paths.get(Settings.getRootDirectory() + "/logs/logfile.txt").normalize());
        return new Response.Builder(200, logs).build();
    }
}
