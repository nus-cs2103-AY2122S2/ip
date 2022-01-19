import java.util.ArrayList;

/**
 * Stores and handles the list of tasks
 */
public class Notebook {
    private ArrayList<Task> tasks;

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
        } else if (e.startsWith("mark") || e.startsWith("unmark")) {
            e = this.marker(e);
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
        tasks.add(new Task(e));
        return "Added: " + e;
    }

    /**
     * Lists out the tasks stored in the tasks Arraylist.
     * @return A string version of the list formatted with numbering.
     */
    private String listOut() {
        StringBuilder temp = new StringBuilder("Here are the tasks in your list: \n");
        int tempCounter = 1;

        for (Task a : tasks) {
            temp.append(tempCounter).append(". ").append(a).append("\n");
            tempCounter++;
        }
        return temp.toString();
    }

    /**
     * Marks or Unmark the indicated task
     * @param e The instruction to follow with the task number
     * @return The string result of the instruction
     */
    private String marker(String e) {
        String[] temp = e.split(" ");

        try {
            Task currTask = tasks.get(Integer.parseInt(temp[1]) - 1);
            if (temp[0].equals("mark")) {
                currTask.mark();
                return "Good job! Task Completed \n" + currTask;
            } else {
                currTask.unmark();
                return "Alright, I will unmark this \n" + currTask;
            }
        } catch (IndexOutOfBoundsException a) {
            return "Invalid task number. Please try again";
        }
    }
}
