package chatbot.task;

import chatbot.util.Storage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An ordered collection of tasks.
 */
public class TaskList extends ArrayList<Task> implements Serializable {
    private final String saveFile;

    private TaskList(String saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Constructs a task list with a specified file to load from and save to.
     *
     * @param saveFile the save file location
     */
    public static TaskList create(String saveFile) {
        TaskList taskList = Storage.Load(saveFile);
        if (taskList == null) {
            taskList = new TaskList(saveFile);
        }
        return taskList;
    }

    public static TaskList create() {
        return new TaskList("");
    }

    @Override
    public boolean add(Task task) {
        boolean ret = super.add(task);
        if (!saveFile.isBlank()) {
            Storage.Save(saveFile, this);
        }
        return ret;
    }

    @Override
    public Task remove(int index) {
        Task ret = super.remove(index);
        Storage.Save(saveFile, this);
        if (!saveFile.isBlank()) {
            Storage.Save(saveFile, this);
        }
        return ret;
    }

    @Override
    public void clear() {
        super.clear();
        Storage.Save(saveFile, this);
        if (!saveFile.isBlank()) {
            Storage.Save(saveFile, this);
        }
    }

    /**
     * Find all tasks in the list containing the specified keyword.
     *
     * @param keyword the keyword to search for in the list
     * @return an array containing the found tasks
     */
    public Task[] find(String keyword) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : this) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                tasks.add(t);
            }
        }
        return tasks.toArray(new Task[0]);
    }
}