import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileRead {

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (!s.hasNext()) {
            System.out.println("You have no saved tasks.");
        } else {
            System.out.println("Here are your saved tasks:");
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        }
    }
}
