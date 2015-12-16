package http.assets;

import http.configuration.Settings;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;

public class FileUtil {

    public static byte[] readFileContents(String path) throws IOException {
        return Files.readAllBytes(new File(Settings.PUBLIC_DIR, path).toPath().normalize());
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
}
