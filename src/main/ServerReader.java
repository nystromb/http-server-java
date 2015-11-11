package main;

import java.io.File;

/**
 * Created by nystrom on 11/10/15.
 */
public class ServerReader {

    public static String getContent(File path) {
        StringBuffer contents = new StringBuffer();
        if(path.isDirectory()){
            String[] files = path.list();
            for(String file : files)
                contents.append(file + " ");
        }
        return contents.toString();
    }
}
