package duke2;

import java.io.IOException;

import static duke2.Parser.writeToFile;

public class Error {

    public static void error(String path, Task t) {
        try {
            writeToFile(path, t.toString());
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
}
