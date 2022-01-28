import java.io.IOException;
import java.util.ArrayList;
/**
 * This is a DukeList class that handles the operations to the
 * tasks in the list in the Duke system
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
    public Task delete(int i) throws DukeException {
        Task task;
        try {
            task = tasks.get(i - 1);
            tasks.remove(i - 1);
        } catch (Exception e) {
            StringBuilder error = new StringBuilder();
            error.append(line ).append("☹ OOPS!!! Fam, you do not even have this numbered Task in your list.\n");
            error.append(line);
            throw new DukeException(error.toString());
        }
        return task;
    }

    /**
     * Displays all Tasks in the list
     */
    public String printTasks() {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        res.append(line);
        res.append("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i ++) {
            res.append(String.format("%o.", i + 1));
            res.append(tasks.get(i).toString());
        }
        res.append(line);
        return res.toString();
    }

    /**
     * Marks the Task as done by given index input.
     * Index is based on the position the Task is in, in the list
     */
    public Task markTask(int i) {
        Task task = tasks.get(i - 1);
        task.setDone(1);
        return task;
    }

    /**
     * Unmarks the Task as done by given index input.
     * Index is based on the position the Task is in, in the list
     */
    public Task unmarkTask(int i) {
        Task task = tasks.get(i - 1);
        task.setDone(0);
        return task;
    }
}
