package node23.Services;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogReader {
    public static ArrayList<String> ReadLog() {
        ArrayList<String> logLines = new ArrayList<String>();
        Path filePath = Paths.get("games.log");
        Charset charset = StandardCharsets.UTF_8;
        try {
            List<String> lines = Files.readAllLines(filePath, charset);
            for (String line : lines) {
                logLines.add(line);
            }
        } catch (IOException ex) {
            System.out.format("I/O error: %s%n", ex);
        }
        return logLines;
    }
}
