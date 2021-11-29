package node23;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import node23.Services.LogReader;

public class LogReaderTest {
    @Test
    public void ReadAllLogLinesTest() {
        ArrayList<String> logLines = LogReader.ReadLog();
        assertEquals(5306, logLines.size());
    }
}
