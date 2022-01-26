import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * class FileRead to read data from a file
 */
public class FileRead {
    /**
     * Prints each line from specified file
     * @param filePath
     */
    public static String loadFileContents(String filePath) {
        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            if (!s.hasNext()) {
                System.out.println("You have no saved tasks.");

            } else {
                String output = "";
                while (s.hasNext()) {
                    output += s.nextLine() + "\n";
                }
                return output;
            }
            return "";
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return "";
        }
    }
}
