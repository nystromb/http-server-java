package http.Assets;

import http.Configuration.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nystrom on 11/10/15.
 */
public class FileUtil {

    public static byte[] readFileContents(String path) throws IOException {
        return Files.readAllBytes(new File(Settings.getRootDirectory(), path).toPath().normalize());
    }

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

    public static String getDirectoryFileList(String path) throws IOException{
        StringBuffer contents = new StringBuffer();

        try(DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path), "[aA-zZ]*")){
            for(Path file : stream) {
                contents.append(file.getFileName() + " ");
            }
        }

        return contents.toString();
    }

}
