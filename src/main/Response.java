package main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
/**
 * Created by nystrom on 11/23/15.
 */
public class Response {
    private String version = "HTTP/1.1";
    private String status = "";
    private HashMap<String, String> headers = new HashMap<>();
    private byte[] body = "".getBytes();

    public void setStatus(String status) {
        this.status = status;
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        buffer.write((version + " " + status + "\r\n").getBytes());

        for(Entry<String, String> header : headers.entrySet()){
            buffer.write((header.getKey() + ": " + header.getValue() + "\r\n").getBytes());
        }

        buffer.write("\r\n".getBytes());
        buffer.write(this.body);

        return buffer.toByteArray();
    }

    public void setBody(byte[] body) {
        this.body = body;
        addHeader("Content-Length", String.valueOf(body.length));
    }

    public byte[] getBody() {
        return body;
    }

    public String getHeader(String header) {
        return headers.get(header);
    }

    public void addHeader(String header, String value) {
        headers.put(header, value);
    }
}
