package main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

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

        try(DirectoryStream<Path> stream = Files.newDirectoryStream(path.toPath(), "[aA-zZ]*")){
            for(Path file : stream) {
                contents.append(file.getFileName() + " ");
            }
        }

        return contents.toString();
    }

    public static byte[] readImageContents(File path) throws IOException{
        return Files.readAllBytes(path.toPath());
    }
}
