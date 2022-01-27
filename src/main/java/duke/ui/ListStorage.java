package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskManager;

import java.io.*;
import java.util.ArrayList;

/**
 * ListStorage Object that handles storage of Tasks in a ser file.
 * Checks for presence of the storage directory.
 * Creates storage directory if absent.
 * Creates ser file if absent.
 */
public class ListStorage {
    ArrayList<Task> taskList;

    /**
     * Constructs the ListStorage Object.
     *
     * @param taskManager TaskManager used for getting task list
     */
    public ListStorage(TaskManager taskManager) {
        this.taskList = taskManager.getList();
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
        writeStream.writeObject(this.taskList);
        writeStream.flush();
        writeStream.close();
    }
}
