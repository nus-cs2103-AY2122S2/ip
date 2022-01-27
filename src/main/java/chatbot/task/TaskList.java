package chatbot.task;

import chatbot.util.Storage;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList extends ArrayList<Task> implements Serializable {
    private final String saveFile;

    private TaskList(String saveFile) {
        this.saveFile = saveFile;
    }

    public static TaskList create(String saveFile) {
        TaskList taskList = Storage.<TaskList>Load(saveFile);
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
        if (!saveFile.isBlank()) {
            Storage.Save(saveFile, this);
        }
        return ret;
    }

    @Override
    public void clear() {
        super.clear();
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
            if (t.toString().contains(keyword)) {
                tasks.add(t);
            }
        }
        return tasks.toArray(new Task[0]);
    }
}