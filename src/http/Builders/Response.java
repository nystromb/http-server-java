package http.builders;

import http.registry.ResponseCodes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

public class Response {
    private final String PROTOCOL = "HTTP/1.1";
    private HashMap<Integer, String> statusCodeMap = new ResponseCodes();


    public String statusLine = "";
    public HashMap<String, String> headers;
    public byte[] body = "".getBytes();

    public Response(Builder builder){
        this.statusLine = PROTOCOL + " " + String.valueOf(builder.status) + " " + statusCodeMap.get(builder.status);
        this.headers = builder.headers;
        this.body = builder.body;
    }

    public static class Builder {
        public int status;
        public HashMap<String, String> headers = new HashMap<>();
        public byte[] body = "".getBytes();

        public Builder() {

        }

        public Builder(int code, String body){
            this.status = code;
            this.body = body.getBytes();
            headers.put("Content-Length", String.valueOf(this.body.length));
        }

        public Builder(int code, byte[] body) {
            this.status = code;
            this.body = body;
            headers.put("Content-Length", String.valueOf(this.body.length));
        }

        public Builder(int code) {
            this.status = code;
            this.body = "".getBytes();
        }

        public Builder status(int code) {
            status = code;
            return this;
        }

        public Builder addHeader(String header, String value){
            this.headers.put(header, value);
            return this;
        }

        public Builder setBody(byte[] contents) {
            body = contents;
            return this;
        }

        public Builder setBody(String contents) {
            body = contents.getBytes();
            return this;
        }

        public Response build(){
            return new Response(this);
        }
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        buffer.write((statusLine + "\r\n").getBytes());

        for(Entry<String, String> header : headers.entrySet()){
            buffer.write((header.getKey() + ": " + header.getValue() + "\r\n").getBytes());
        }

        buffer.write("\r\n".getBytes());
        buffer.write(this.body);

        return buffer.toByteArray();
    }
}
