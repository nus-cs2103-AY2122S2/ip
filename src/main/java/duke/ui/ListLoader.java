package duke.ui;

import duke.tasks.Task;
import duke.tasks.TaskManager;

import java.io.*;
import java.util.ArrayList;

/**
 * ListLoader Object that handles loading of task list from
 * a saved ser file during program initialisation.
 */
public class ListLoader {
    private TaskManager taskManager;

    /**
     * Constructs ListLoader Object.
     *
     * @param manager TaskManager to pass saved task list elements
     */
    public ListLoader(TaskManager manager) {
        this.taskManager = manager;
    }

    /**
     * Loads saved task list into TaskManager Object.
     *
     * @throws IOException if ser file does not exist
     * @throws ClassNotFoundException if Task Objects in stored list are not serializable
     */
    public void loadList() throws IOException, ClassNotFoundException {
        FileInputStream readData = new FileInputStream("storage/list.ser");
        ObjectInputStream readStream = new ObjectInputStream(readData);
        ArrayList<Task> taskList = (ArrayList<Task>) readStream.readObject();
        readStream.close();
        this.taskManager.loadList(taskList);
    }
}
