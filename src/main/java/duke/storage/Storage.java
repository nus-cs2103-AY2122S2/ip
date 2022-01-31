package duke.storage;

import duke.FileHandler;
import duke.task.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a storage class that holds file
 * accessing and editing methods.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for a Storage object.
     *
     * @param path The file path of a file that will be used by
     *             the storage object.
     */
    public Storage(String path) {
        this.filePath = path;
    }

    /**
     * Writes some input text to a file.
     *
     * @param input A string with the text to be written.
     * @throws IOException If I/O error occurs.
     */
    public void writeToFile(String input) throws IOException {
        FileHandler.writeToFile(this.filePath, input);
    }

    /**
     * Reads all text from the storage file.
     *
     * @return An ArrayList with tasks objects loaded from a file.
     * @throws FileNotFoundException If file being read does not exist.
     */
    public ArrayList<Task> retrieveData() throws FileNotFoundException {
        ArrayList<Task> arr = new ArrayList<Task>();
        FileHandler.readFromFile(arr);
        return arr;
    }

    /**
     * Edits lines from a file.
     *
     * @param oldText The line to be replaced in the file.
     * @param newText The line that will replace the old line in the file.
     * @throws IOException If I/O error occurs.
     */
    public void editData(String oldText, String newText) throws IOException {
        FileHandler.editFile(this.filePath, oldText, newText);
    }

    /**
     * Removes all lines from a file.
     *
     * @throws IOException If I/O error occurs.
     */
    public void clearData() throws IOException {
        FileHandler.clearFile(this.filePath);
    }

}
