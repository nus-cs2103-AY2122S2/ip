import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * class FileWrite to write data into a file
 */
public class FileWrite {
    /**
     * Writes a String into the specified file
     * @param filePath
     * @param textToAdd
     * @throws IOException
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            new File("data").mkdirs();
            FileWriter fw1 = new FileWriter(filePath);
            fw1.write(textToAdd);
            fw1.close();
        }
    }
}

