package duke.file_management;

import java.io.FileWriter;
import java.io.IOException;

/**
 * To write data into "duke.txt" file.
 *
 * @author Justin Ng Jie Ern
 */
public class WriteFile {
    /**
     * File name for file to be written on in local device.
     */
    private final String pathName;

    /**
     * Constructor to write data into file.
     */
    public WriteFile() {
        this.pathName = "duke.txt";
    }

    /**
     * Method to clear the data that is on the file.
     */
    public void clearFile() {
        try {
            FileWriter fileWriter = new FileWriter(this.pathName, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to write data onto file.
     *
     * @param text Contains the information that is to be written on the file.
     */
    public void writeToFile(String text){
        try {
            FileWriter fileWriter = new FileWriter(this.pathName, true);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
