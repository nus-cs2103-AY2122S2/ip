import java.util.ArrayList;

/**
 * Stores and handles the list of tasks
 */
public class Notebook {
    private ArrayList<String> tasks;

    /**
     * Constructor to initialise a new task list.
     */
    public Notebook() {
        tasks = new ArrayList<>();
    }

    /**
     * Uses input string e to determine appropriate action.
     * If input is "list", will execute listOut method. If not, adds
     * instruction to tasks list.
     * @param e The instruction to follow
     * @return The string result
     */
    public String instruction(String e) {
        if (e.equals("list")) {
            e = this.listOut();
        } else {
            e = this.add(e);
        }
        return e;
    }

    /**
     * Adds a task to the list
     * @param e The task to be added
     * @return A string conformation of the input.
     */
    private String add(String e) {
        tasks.add(e);
        return "Added: " + e;
    }

    /**
     * Lists out the tasks stored in the tasks Arraylist.
     * @return A string version of the list formatted with numbering.
     */
    private String listOut() {
        StringBuilder temp = new StringBuilder();
        int tempCounter = 1;

        for (String a : tasks) {
            temp.append(tempCounter).append(". ").append(a).append("\n");
            tempCounter++;
        }
        return temp.toString();
    }
}
