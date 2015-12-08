package main;


import java.io.IOException;

public interface HttpExchange {
    Response exchange(Request request) throws IOException;
}
