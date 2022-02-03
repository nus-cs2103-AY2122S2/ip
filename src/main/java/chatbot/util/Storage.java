package chatbot.util;

import java.io.File;

import chatbot.exception.ChatBotException;


/**
 * Represents a save file handler.
 */
public class Storage {

    private final File file;

    /**
     * Instantiates a new Storage.
     *
     * @param directoryPath The path of the directory containing the save file.
     * @param fileName      The name of the save file.
     */
    public Storage(String directoryPath, String fileName) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File f = new File(dir, fileName);
        try {
            f.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.file = f;
        }
    }

    /**
     * Save changes.
     *
     * @param taskList The task list to save in the save file.
     * @throws ChatBotException If I/O issues occur when writing to save file.
     */
    public void saveChanges(TaskList taskList) throws ChatBotException {
        taskList.save(file);
    }

    /**
     * Load data.
     *
     * @param taskList The task list to load the save file data into.
     * @throws ChatBotException If I/O issues occur when reading the save file
     * or if data in the save file is corrupted.
     */
    public void loadData(TaskList taskList) throws ChatBotException {
        taskList.load(file);
    }
}
