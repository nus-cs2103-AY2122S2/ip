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

    /**
     * Adds Tasks into the list
     */
    public String add(Task t) {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
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
    public String delete(int i) throws DukeException {
        Task t;
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        try {
            t = tasks.get(i - 1);
            tasks.remove(i - 1);
        } catch (Exception e) {
            StringBuilder error = new StringBuilder();
            error.append("\n____________________________________________________________\n").append("☹ OOPS!!! Fam, you do not even have this numbered Task in your list.\n");
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
    public String markTask(int i) {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        res.append(line).append("Nice! I've marked this task as done: \n");
        Task t = tasks.get(i - 1);
        t.setDone(true);
        res.append(t.toString()).append(line);
        return res.toString();
    }

    /**
     * Unmarks the Task as done by given index input.
     * Index is based on the position the Task is in, in the list
     */
    public String unmarkTask(int i) {
        StringBuilder res = new StringBuilder();
        String line = "____________________________________________________________ \n";
        res.append(line).append("OK, I've marked this task as not done yet: \n");
        Task t = tasks.get(i - 1);
        t.setDone(false);
        res.append(t.toString()).append(line);
        return res.toString();
    }
}
