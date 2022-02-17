package duke.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * ListStorage Object that handles storage of Tasks in a ser file.
 * Checks for presence of the storage directory.
 * Creates storage directory if absent.
 * Creates ser file if absent.
 */
public class ListStorage {

    /**
     * Constructs the ListStorage Object.
     */
    public ListStorage() {
    }

    /**
     * Saves task list into text file.
     *
     * @param taskList ArrayList containing tasks
     * @throws IOException if Task objects in ArrayList are not serializable
     */
    public void saveList(ArrayList<Task> taskList) throws IOException {
        File file = new File("storage");
        if (!file.exists()) {
            file.mkdir();
        }
        FileOutputStream writeData = new FileOutputStream("storage/list.ser");
        ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
        writeStream.writeObject(taskList);
        writeStream.flush();
        writeStream.close();
    }
}
