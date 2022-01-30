package fileHandling;

import java.io.FileWriter;
import java.io.IOException;

public class FilesWriter {
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
