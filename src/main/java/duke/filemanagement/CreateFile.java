package duke.filemanagement;

import java.io.File;
import java.io.IOException;

/**
 * To create a file in the user's local device.
 *
 * @author Justin Ng Jie Ern
 */
public class CreateFile {
    /**
     * File name for file created in local device.
     */
    private String fileName;

    /**
     * Constructor to create the file.
     */
    public CreateFile() {
        this.fileName = "duke.txt";
    }

    /**
     * Getter function to obtain the name of the file.
     *
     * @return The file name "duke.txt".
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * To create the file.
     * If a file with name "duke.txt" does not exist, then file is created and true is returned.
     * If a file with name "duke.txt" exists, then nothing is created and false is returned.
     *
     * @return True if file does not exist and its created through this function. False if file exists already.
     */
    public boolean createFile() {
        boolean isCreated = false;
        try {
            File file = new File(this.fileName);
            isCreated = file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isCreated;
    }
}
