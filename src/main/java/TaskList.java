import java.io.FileNotFoundException;
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

public class DukeList {
    protected ArrayList<Task> tasks = new ArrayList<>();
    String line = "____________________________________________________________ \n";

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds Tasks into the list
     */
    public String add(Task t, FileAction f) {
        StringBuilder res = new StringBuilder();
        tasks.add(t);
        res.append(line).append("Got it. I've added this task: \n");
        res.append(t.toString()).append("\n");
        res.append("Now you have ").append(tasks.size());
        res.append(" tasks in the list.\n").append(line);
        return res.toString();
    }

    /**
     * Deletes Tasks from the list
     */
    public String delete(int i, FileAction f) throws DukeException {
        Task t;
        StringBuilder res = new StringBuilder();
        try {
            t = tasks.get(i - 1);
            tasks.remove(i - 1);
        } catch (Exception e) {
            StringBuilder error = new StringBuilder();
            error.append(line ).append("☹ OOPS!!! Fam, you do not even have this numbered Task in your list.\n");
            error.append("____________________________________________________________\n");
            throw new DukeException(error.toString());
        }
        res.append(line).append("Noted. I've removed this task: \n");
        res.append(t.toString()).append("\n");
        res.append("Now you have ").append(tasks.size());
        res.append(" tasks in the list.\n").append(line);
        return res.toString();
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
    public String markTask(int i, FileAction f) {
        StringBuilder res = new StringBuilder();
        res.append(line).append("Nice! I've marked this task as done: \n");
        Task t = tasks.get(i - 1);
        t.setDone(1);
        res.append(t.toString()).append(line);
        return res.toString();
    }

    /**
     * Unmarks the Task as done by given index input.
     * Index is based on the position the Task is in, in the list
     */
    public String unmarkTask(int i, FileAction f) {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        res.append(line).append("OK, I've marked this task as not done yet: \n");
        Task t = tasks.get(i - 1);
        t.setDone(0);
        res.append(t.toString()).append(line);
        return res.toString();
    }

    /**
     * Saves the current instances of Task items in Tasks list into
     * provided text file
     */
    public void saveAllTasks(FileAction f) throws IOException {
        f.startWriter();
        for (Task t : tasks) {
            f.saveFile(t.toString());
        }
    }
}
