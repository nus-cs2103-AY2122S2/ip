/**
 * Encapsulates a task-list.
 */
public class TaskList {

    /** Array of task. */
    private final Task[] tasks;

    /** 1-based length of tasks. */
    private int count;

    /**
     * Constructor for TaskList class.
     * 1-based array.
     */
    public TaskList() {
        this.tasks = new Task[101];
        this.count = 1;
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
                time = split[1].substring(4); // time
                cur = new Event(split[0], time);
                break;
        }
        tasks[count++] = cur;
        System.out.println("        Got it. I've added this task:");
        System.out.println("  " + this.taskStatus(count-1));
        System.out.printf("        Now you have %d tasks in the list.%n", count-1);
    }

    /**
     * Returns string representation of task index and description.
     *
     * @param i Task index.
     *
     * @return String representation of task index and description.
     */
    private String taskStatus(int i) {
        return String.format("        %d. %s", i, tasks[i].toString());
    }

    /**
     * Marks task.
     *
     * @param i Task index.
     */
    public void mark(int i) {
        try {
            tasks[i].mark();
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
            tasks[i].unmark();
            System.out.println("        OK! The task is unmarked:");
            System.out.println("  " + this.taskStatus(i));
        } catch (Exception e) {
            System.out.println("        Task does not exist!");
        }
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
        for (int i = 1; i < tasks.length; i++) {
            if (tasks[i] == null) break;
            sb.append(prefix);
            prefix = "\n";
            sb.append(this.taskStatus(i));
        }
        return sb.toString();
    }
}
