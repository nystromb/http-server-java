package test.assets;

import http.assets.FileUtil;
import http.configuration.Settings;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class FileUtilTest {

    @Test
    public void testGetsContentsOfTextFile() throws IOException {
        assertArrayEquals("file1 contents".getBytes(), FileUtil.readFileContents("/file1"));
        assertEquals("This is a file that contains text to read part of in order to fulfill a 206.\n", FileUtil.readFileContents(new File(Settings.PUBLIC_DIR, "partial_content.txt")));

    }
}
