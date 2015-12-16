package http;

import http.builders.Request;
import http.builders.Response;
import http.configuration.Settings;

import java.io.File;
import java.io.IOException;

public class DirectoryHandler extends ApplicationController {

    @Override
    protected Response get(Request request) throws IOException {
        String contents = "<!DOCTYPE html><html><head></head><body><ul>";
        for(String file : new File(Settings.getRootDirectory(), request.getPath()).list()){
            contents += "<li><a href=\"/" + file +"\">" + file + "</a></li>";
        }
        contents += "</ul></body>";
        return new Response.Builder(200, contents).build();
    }
}
