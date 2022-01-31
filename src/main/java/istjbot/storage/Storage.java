package istjbot.storage;

import istjbot.exception.BotException;
import istjbot.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Encapsulates the concept of a storage.
 * Responsible for saving and loading the user's tasks using an external file.
 */
public class Storage {
    /** File that saves the tasks set by the user. */
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
     * Saves all the tasks into the file.
     *
     * @param tasks TaskList to refer to all the tasks.
     * @throws BotException When the file is not yet initialized.
     */
    public void save(TaskList tasks) throws BotException {
        // Assumption: IstjBox.txt is not tampered with by a user
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(tasks.tasksToTxtString());
            fw.close();

        } catch (IOException e) {
            throw new BotException(e.getMessage());
        }
    }
}



