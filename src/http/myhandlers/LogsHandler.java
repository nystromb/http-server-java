package http.myhandlers;

import http.handlers.ApplicationController;
import http.request.Request;
import http.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class LogsHandler extends ApplicationController {
    @Override
    protected Response get(Request request) throws IOException {
        byte[] logs = Files.readAllBytes(new File(System.getProperty("user.dir"), "/logs/logfile.txt").toPath());
        return new Response.Builder(200, logs).build();
    }
}
