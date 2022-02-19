package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to write data file to hard disk.
 */
public class WriteFile {
    /**
     * Method to write the data file containing the list of tasks to the hard disk.
     * @param s
     */
    public static void writeToFile(String s) {
        try {
            File f = new File("data");
            if (!f.exists()) {
                f.mkdir();
                File file = new File("data/duke.txt");
                if (!file.exists()) {
                    file.createNewFile();
                }
            }        
            
            FileWriter fw = new FileWriter("data/duke.txt");
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            String errorMsg = "Sorry, I was not able to save the changes made to the hard disk... Please try again.";
            System.out.println(errorMsg);
        }
    }
}