package duke.ui;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import duke.tasks.Task;
import duke.tasks.TaskManager;

/**
 * ListLoader Object that handles loading of task list from
 * a saved ser file during program initialisation.
 */
public class ListLoader {
    private final TaskManager taskManager;

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
