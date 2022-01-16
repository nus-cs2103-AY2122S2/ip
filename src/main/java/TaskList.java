import java.util.ArrayList;

/**
 * Encapsulates a task-list.
 */
public class TaskList {

    /** Arraylist of task. */
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
     * 1-based ArrayList array.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task.
     *
     * @param type Type of class
     * @param s Description of task.
     */
    public void add(String type, String s) {
        Task cur = null;
        String time;
        String[] split;
        switch (type) {
            case "todo":
                cur = new Todo(s);
                break;
            case "deadline":
                split = s.split("/");
                time = split[1].substring(3); // time
                cur = new Deadline(split[0], time);
                break;
            case "event":
                split = s.split("/");
                time = split[1].substring(3); // time
                cur = new Event(split[0], time);
                break;
        }
        tasks.add(cur);
        System.out.println("        Got it. I've added this task:");
        System.out.println("  " + this.taskStatus(tasks.size() - 1));
        this.size();
    }

    /**
     * Returns string representation of task index and description.
     *
     * @param i Task index.
     *
     * @return String representation of task index and description.
     */
    private String taskStatus(int i) {
        return String.format("        %d. %s", i+1, tasks.get(i).toString());
    }

    /**
     * Marks task.
     *
     * @param i Task index.
     */
    public void mark(int i) {
        try {
            tasks.get(i).mark();
            System.out.println("        Nice! The task is marked as done:");
            System.out.println("  " + this.taskStatus(i));
        } catch (Exception e) {
            System.out.println("        Task does not exist!");
        }
    }

    /**
     * Unmark task.
     *
     * @param i Task index.
     */
    public void unmark(int i) {
        try {
            tasks.get(i).unmark();
            System.out.println("        OK! The task is unmarked:");
            System.out.println("  " + this.taskStatus(i));
        } catch (Exception e) {
            System.out.println("        Task does not exist!");
        }
    }

    /**
     * Delete task.
     *
     * @param i Task index.
     */
    public void delete(int i) {
        try {
            String status = this.taskStatus(i);
            tasks.remove(i);
            System.out.println("        Noted. I've removed this task: ");
            System.out.println("  " + status);
            this.size();
        } catch (Exception e) {
            System.out.println("        Task does not exist!");
        }
    }

    /**
     * Prints the number of tasks.
     *
     */
    private void size() {
        System.out.printf("        Now you have %d tasks in the list.%n", tasks.size());
    }

    /**
     * String representation of TaskList.
     *
     * @return String representation of TaskList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String prefix = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) break;
            sb.append(prefix);
            prefix = "\n";
            sb.append(this.taskStatus(i));
        }
        return sb.toString();
    }
}
