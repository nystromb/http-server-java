package http;

import http.builders.Request;
import http.builders.Response;
import http.configuration.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.io.File;

public class LogsHandler extends ApplicationController {
    @Override
    protected Response get(Request request) throws IOException {
        byte[] logs = Files.readAllBytes(new File(Settings.PUBLIC_DIR, "/logs/logfile.txt").toPath());
        return new Response.Builder(200, logs).build();
    }
}
