package main;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by nystrom on 11/10/15.
 */
public class FileUtil {

    public static byte[] readFileContents(String path) throws IOException {
        return Files.readAllBytes(new File(ServerSettings.getRootDirectory(), path).toPath());
    }

    public static String getDirectoryFileList(File path) throws IOException{
        StringBuffer contents = new StringBuffer();

        try(DirectoryStream<Path> stream = Files.newDirectoryStream(path.toPath(), "[aA-zZ]*")){
            for(Path file : stream) {
                contents.append(file.getFileName() + " ");
            }
        }

        return contents.toString();
    }
}
