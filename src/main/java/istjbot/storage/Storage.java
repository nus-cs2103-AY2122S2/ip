package istjbot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import istjbot.exception.BotException;
import istjbot.text.TextList;

/**
 * Encapsulates the concept of a storage.
 * Responsible for saving and loading the user's texts using an external file.
 */
public class Storage {
    /** File that saves the texts set by the user. */
    private final File file;

    /**
     * Constructor for this Storage.
     * Takes in the specified file path and creates a new directory/file if the
     * specified directory/file does not exist, or else loads an existing file.
     *
     * @param filePath String of the path of the file.
     * @throws BotException When an I/O error is occurred.
     */
    public Storage(String filePath) throws BotException {
        this.file = new File(filePath);
        File directory = new File(this.file.getParentFile().getAbsolutePath());
        directory.mkdirs();
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new BotException(e.getMessage());
            }
        }
    }

    /**
     * Returns the file that has been initialized by the constructor.
     *
     * @return File initialized by the constructor.
     */
    public File load() {
        return this.file;
    }

    /**
     * Saves all the tasks and notes into the file.
     *
     * @param texts TextList to refer to all the tasks and notes.
     * @throws BotException When the file is not yet initialized.
     */
    public void save(TextList texts) throws BotException {
        // The file should be new or not tampered with by a user
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(texts.textsToTxtString());
            fw.close();

        } catch (IOException e) {
            throw new BotException(e.getMessage());
        }
    }
}



