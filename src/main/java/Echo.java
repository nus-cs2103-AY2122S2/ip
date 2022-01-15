import java.util.Scanner;

public class Echo {

    /** Task. */
    private final Task task;

    /**
     * Constructor for Task class.
     */
    public Echo() {
        this.task = new Task();
    }
    /**
     * Add task and replies that it has been added.
     *
     * @param s String to be printed.
     */
    public void addTask(String s) {
        task.add(s);
        System.out.println("        added: " + s);
    }

    /**
     * Returns task.
     */
    public void getTask() {
        System.out.println(task.toString());
    }

    /**
     * Prints goodbye.
     */
    public void exit() {
        System.out.println("        Goodbye!");
    }
}
