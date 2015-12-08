package main;


import main.Builders.Request;
import main.Builders.Response;

import java.io.IOException;

public interface HttpExchange {
    Response exchange(Request request) throws IOException;
}
