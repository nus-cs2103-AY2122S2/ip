package duke.modules;

import java.util.ArrayList;

/**
 * Represents a users list of tasks.
 */
public class TaskList {
    private ArrayList<Task> toDoList;

    /**
     * Constructs a new TaskList by directly inputting an ArrayList of Tasks.
     *
     * @param manualList The list to initialise the TaskList with.
     */
    public TaskList(ArrayList<Task> manualList) {
        this.toDoList = manualList;
    }

    /**
     * Constructs a new TaskList by loading from storage.
     *
     * @param storage The Storage object that contains the path to the desired ArrayList of Tasks.
     */
    public TaskList(Storage storage) {
        toDoList = storage.load();
    }

    /**
     * Returns the ArrayList of Tasks associated with a taskList.
     *
     * @return the users list of tasks.
     */
    public ArrayList<Task> getToDoList() {
        return toDoList;
    }

    /**
     * Adds a task to the users list of tasks.
     *
     * @param task The Task object to be added.
     */
    public void addTask(Task task) {
        assert task != null;
        toDoList.add(task);
    }

    /**
     * Removes a task from the users list of tasks.
     *
     * @param index The index of the task to be removed.
     */
    public void removeTask(int index) {
        toDoList.remove(index);
    }
}
