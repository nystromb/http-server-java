package main;

import main.Request;
import main.Response;

import java.io.IOException;

public interface HttpExchange {
    Response exchange(Request request) throws IOException;
}
