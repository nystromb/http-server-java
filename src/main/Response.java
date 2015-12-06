package main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map.Entry;
/**
 * Created by nystrom on 11/23/15.
 */
public class Response {
    public String status = "";
    public HashMap<String, String> headers;
    public byte[] body = "".getBytes();

    public Response(Builder builder){
        this.status = builder.status;
        this.headers = builder.headers;
        this.body = builder.body;
    }

    public Hashtable<Integer, String> statusCodes = new Hashtable<Integer, String>();

    public static class Builder {
        private final String PROTOCOL = "HTTP/1.1";
        private HashMap<Integer, String> statusCodeMap = new HttpResponseCodes();

        public String status;
        public HashMap<String, String> headers = new HashMap<>();
        public byte[] body = "".getBytes();

        public Builder(int code, String body){
            this.status = PROTOCOL + " " + String.valueOf(code) + " " + statusCodeMap.get(code);
            this.body = body.getBytes();
            headers.put("Content-Length", String.valueOf(this.body.length));
        }

        public Builder(int code, byte[] body) {
            this.status = PROTOCOL + " " + String.valueOf(code) + " " + statusCodeMap.get(code);
            this.body = body;
            headers.put("Content-Length", String.valueOf(this.body.length));
        }

        public Builder(int code) {
            this.status = PROTOCOL + " " + String.valueOf(code) + " " + statusCodeMap.get(code);
            this.body = "".getBytes();
        }

        public Builder addHeader(String header, String value){
            this.headers.put(header, value);
            return this;
        }

        public Response build(){
            return new Response(this);
        }

        public void setBody(String contents) {
            body = contents.getBytes();
        }

        public Builder status(int code) {
            status = PROTOCOL + " " + String.valueOf(code) + " " + statusCodeMap.get(code);
            return this;
        }
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        buffer.write((status + "\r\n").getBytes());

        for(Entry<String, String> header : headers.entrySet()){
            buffer.write((header.getKey() + ": " + header.getValue() + "\r\n").getBytes());
        }

        buffer.write("\r\n".getBytes());
        buffer.write(this.body);

        return buffer.toByteArray();
    }
}
