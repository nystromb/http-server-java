package test;

import main.FileUtil;
import main.ServerSettings;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 11/10/15.
 */
public class FileUtilTest {

    @Before
    public void setUp() throws Exception {
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/my-8thlight-apprenticeship/cob_spec/public/"});
    }

    @Test
    public void testGetsListOfFilesInDirectory() throws IOException{
        assertEquals("file1 file2 image.gif image.jpeg image.png logs partial_content.txt patch-content.txt text-file.txt ", FileUtil.getDirectoryFileList(ServerSettings.getRootDirectory() + "/"));
    }

    @Test
    public void testGetsContentsOfTextFile() throws IOException {
        assertArrayEquals("file1 contents".getBytes(), FileUtil.readFileContents("/file1"));
    }
}
