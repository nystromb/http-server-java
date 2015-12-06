package test;

import main.FileUtil;
import main.Request;
import main.ServerSettings;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by nystrom on 11/10/15.
 */
public class FileUtilTest {

    @Before
    public void setUp() throws Exception {
        ServerSettings.parse(new String[]{"-d", "/Users/nystrom/Documents/cob_spec/public/"});
    }

    @Test
    public void testGetsContentsOfTextFile() throws IOException {
        assertArrayEquals("file1 contents".getBytes(), FileUtil.readFileContents("/file1"));
        assertEquals("This is a file that contains text to read part of in order to fulfill a 206.\n", FileUtil.readFileContents(new File(ServerSettings.getRootDirectory(), "partial_content.txt")));

    }
}
