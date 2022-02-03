package duke;

import java.util.ArrayList;
/**
 * This is a DukeList class that handles the operations to the
 * tasks in the list in the duke.Duke system
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-15
 */

public class TaskList {
    protected ArrayList<Task> tasks = new ArrayList<>();
    String line = "____________________________________________________________ \n";

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the particular task
     */
    public Task getParticularTask(int i) {
        return tasks.get(i);
    }

    /**
     * Returns the size of the current tasklist
     */
    public int getTaskListSize() {
        return tasks.size();
    }

    /**
     * Adds Tasks into the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes Tasks from the list
     */
    public Task deleteTask(int i) throws DukeException {
        return tasks.remove(i - 1);
    }

    /**
     * Marks the duke.Task as done by given index input.
     * Index is based on the position the duke.Task is in, in the list
     */
    public Task markTask(int i) {
        Task task = tasks.get(i - 1);
        task.setDone(1);
        return task;
    }

    /**
     * Unmarks the duke.Task as done by given index input.
     * Index is based on the position the duke.Task is in, in the list
     */
    public Task unmarkTask(int i) {
        Task task = tasks.get(i - 1);
        task.setDone(0);
        return task;
    }
}
