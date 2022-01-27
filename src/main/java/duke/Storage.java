package duke;

import duke.exception.LoadException;
import duke.exception.RonException;

import java.io.File;
import java.io.IOException;

/**
 * Creates a .txt file to save tasks from Chatbot in the hard disk automatically
 */
public class Storage {
    protected final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Attempts to load existing backup file from given directory, else creates a new file
     *
     * @return file with prefixed file path
     * @throws RonException
     */
    public File load() throws RonException {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new LoadException();
        }
        return file;
    }
}
