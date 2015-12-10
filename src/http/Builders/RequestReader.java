package http.Builders;

import java.io.*;

public class RequestReader {
    public static String read(BufferedReader input) throws IOException{
        StringBuffer request = new StringBuffer();

        String line;
        while((line = input.readLine()) != null){
            request.append(line + "\r\n");

            if(line.isEmpty()) {
                while(input.ready()) request.append((char) input.read());
                break;
            }
        }

        return request.toString();
    }
}
