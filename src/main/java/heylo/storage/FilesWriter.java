package heylo.storage;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles writing from files.
 */
public class FilesWriter {
    /**
     * Writes the given task to the data file.
     *
     * @param task Task added by the user.
     */
    public static void writeToFile(String task) {
        try {
            FileWriter fw = new FileWriter("data/heylo.txt", true);
            fw.write(task);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
