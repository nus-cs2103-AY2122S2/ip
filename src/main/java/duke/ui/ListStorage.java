package duke.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import duke.tasks.TaskManager;

/**
 * ListStorage Object that handles storage of Tasks in a ser file.
 * Checks for presence of the storage directory.
 * Creates storage directory if absent.
 * Creates ser file if absent.
 */
public class ListStorage {
    private TaskManager taskManager;

    /**
     * Constructs the ListStorage Object.
     *
     * @param taskManager TaskManager used for getting task list
     */
    public ListStorage(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Saves task list into text file.
     *
     * @throws IOException if Task objects in ArrayList are not serializable
     */
    public void saveList() throws IOException {
        File file = new File("storage");
        if (!file.exists()) {
            file.mkdir();
        }
        FileOutputStream writeData = new FileOutputStream("storage/list.ser");
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        writeStream.writeObject(taskManager.getList());
        writeStream.flush();
        writeStream.close();
    }
}
