package duke;

import java.util.ArrayList;

/**
 * This class contains the task list.
 * @author Sim Jun Heng
 * @version CS2103T AY21/22 Sem 2
 */
public class TaskList {
    // ArrayList to store all your tasks
    private ArrayList<Task> list;

    // Constructor
    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Gets a specific task from the list based on position.
     *
     * @param index the position of the task in the list.
     * @return a task object.
     */
    public Task getIndex(int index) {
        return list.get(index);
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    /**
     * Adds task into list.
     *
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Removes task into list.
     *
     * @param index the position of the task in the list.
     * @return the task to be deleted.
     */
    public Task deleteTask(int index) {
        return list.remove(index);
    }
}
