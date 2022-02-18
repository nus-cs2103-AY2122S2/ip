package duke.storage;

import duke.task.Task;

import java.util.ArrayList;
public class TaskList {

    public ArrayList<Task> list;

    /**
     * Constructor for TaskList. Initialises an empty ArrayList<Task>
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds Task to TaskList
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Removes Task from TaskList
     *
     * @param index index of Task to be removed
     */
    public void remove(int index) {
        this.list.remove(index);
    }

    /**
     * Gets task by index
     *
     * @param index index of Task to be retrieved
     * @return Retrieved Task
     */
    public Task get(int index) {
        return this.list.get(index);
    }

    /**
     * Returns Size of TaskList
     *
     * @return integer size of TaskList
     */
    public int size() {
        return this.list.size();
    }
}
