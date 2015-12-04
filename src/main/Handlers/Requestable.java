package main.Handlers;

import main.Request;
import main.Response;

import java.io.IOException;

public interface Requestable {
    Response getResponse(Request request) throws IOException;
}
