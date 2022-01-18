import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> listOfTasks;
    private int numberOfTasks;

    /**
     * Initializes a new instance of list of tasks, according to the size specified.
     *
     * @param size Size of list of tasks.
     */
    public TaskList(int size) {
        this.listOfTasks = new ArrayList<>(size);
        this.numberOfTasks = 0;
    }

    /**
     * Retrieves the current amount of tasks in the list.
     *
     * @return the total number of tasks in the list.
     */
    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    /**
     * Iterates through the list and prints out each task that is on the list.
     */
    public void display() {
        System.out.println("    ____________________________________________________________\n"
                + "     Here are the tasks in your list:\n");
        for (int i = 1; i <= this.numberOfTasks; i++) {
            Task currTask = this.listOfTasks.get(i - 1);
            String output = "     " + i + "." + currTask + "\n";
            System.out.println(output);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * Marks task by changing its status to "done"
     *
     * @param taskId The taskID that corresponds to the task that is to be marked.
     */
    public void mark(int taskId) {
        Task currTask = this.listOfTasks.get(taskId - 1);
        currTask.setStatus(true);
        System.out.println("    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "       [X] " + currTask.getDescription() + "\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Unmarks task by changing its status to "not done".
     *
     * @param taskId The taskID that corresponds to the task that is to be unmarked.
     */
    public void unmark(int taskId) {
        Task currTask = this.listOfTasks.get(taskId - 1);
        currTask.setStatus(false);
        System.out.println("    ____________________________________________________________\n"
                + "     OK, I've marked this task as not done yet:\n"
                + "       [ ] " + currTask.getDescription() + "\n"
                + "    ____________________________________________________________\n");
    }

    /**
     * Adds the to-do task into the list
     *
     * @param userInput Command entered by the user that describes the task.
     */
    public void todo(String userInput) {
        this.numberOfTasks++;

        Task currentTask = new Todo(userInput);
        this.listOfTasks.add(currentTask);

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + currentTask + "\n"
                + "     Now you have " + this.numberOfTasks + " task/s in the list.\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);
    }

    /**
     * Adds the task (which has a deadline) into the list
     *
     * @param userInput Command entered by the user that describes the task.
     */
    public void deadline(String userInput, String by) {
        this.numberOfTasks++;

        Task currentTask = new Deadline(userInput, by);
        this.listOfTasks.add(currentTask);

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + currentTask + "\n"
                + "     Now you have " + this.numberOfTasks + " task/s in the list.\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);
    }

    /**
     * Adds the event task into the list
     *
     * @param userInput Command entered by the user that describes the task.
     */
    public void event(String userInput, String at) {
        this.numberOfTasks++;

        Task currentTask = new Event(userInput, at);
        this.listOfTasks.add(currentTask);

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + currentTask + "\n"
                + "     Now you have " + this.numberOfTasks + " task/s in the list.\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);
    }
}
