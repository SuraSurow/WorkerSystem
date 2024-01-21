package Service;

import java.nio.file.Path;
import java.nio.file.Paths;

public class CurrentDirectory {
    public static Path getCurrentDirectory() {
        return Paths.get("").toAbsolutePath();
    }
}