package test;

import main.ServerReader;
import main.ServerSettings;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 11/10/15.
 */
public class ServerReaderTest {

    @Before
    public void setUp() throws Exception {
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});
    }

    @Test
    public void testGetsListOfFilesInDirectory(){
        File path = new File(ServerSettings.getDirectory() + "/");

        assertEquals("file1 file2 image.gif image.jpeg image.png partial_content.txt patch-content.txt text-file.txt ", ServerReader.getContent(path));
    }

    public void testGetsContentsOfFile(){
        File path = new File(ServerSettings.getDirectory() + "/file1");

        assertEquals("file1 contents", ServerReader.getContent(path));
    }
}
