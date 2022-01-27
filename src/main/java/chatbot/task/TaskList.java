package chatbot.task;

import chatbot.util.Storage;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * An ordered collection of tasks.
 */
public class TaskList extends ArrayList<Task> implements Serializable {
    private final String saveFile;

    public TaskList(String saveFile) {
        this.saveFile = saveFile;
    }

    /**
     * Constructs a task list with a specified file to load from and save to.
     * @param saveFile the save file location
     */
    public static TaskList create(String saveFile) {
        TaskList taskList = Storage.<TaskList>Load(saveFile);
        if (taskList == null) {
            taskList = new TaskList(saveFile);
        }
        return taskList;
    }

    /**
     * Inserts a task to the task list and save the modified list to the save file if a valid save file was specified.
     * @param task the task to add to the list
     * @return true (as specified by Collection.add(E))
     */
    @Override public boolean add(Task task) {
        boolean ret = super.add(task);
        Storage.Save(saveFile, this);
        return ret;
    }

    /**
     * Removes a task from the task list and save the modified list to the save file if a valid save file was specified.
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
    @Override public Task remove(int index) {
        Task ret = super.remove(index);
        Storage.Save(saveFile, this);
        return ret;
    }

    /**
     * Removes all of the elements from this list (optional operation) and save the modified list to the save file if a valid save file was specified.
     * The list will be empty after this call returns.
     */
    @Override public void clear() {
        super.clear();
        Storage.Save(saveFile, this);
    }
}