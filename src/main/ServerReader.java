package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by nystrom on 11/10/15.
 */
public class ServerReader {

    public static String readFileContents(File path){
        String contents = "";
        try {
            FileReader reader = new FileReader(path);
            int character;
            while ((character = reader.read()) != -1) {
                contents += (char) character;
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return contents;
    }

    public static String readDirectoryContents(File path) throws IOException{
        StringBuffer contents = new StringBuffer();

        for(String file : path.list()) {
            contents.append(file + " ");
        }

        return contents.toString();
    }
}
