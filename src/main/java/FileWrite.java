import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

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
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
}

