public class List {
    private final Task[] listOfTasks;
    private int numberOfTasks;

    /**
     * Initializes a new instance of list of tasks, according to the size specified.
     *
     * @param size Size of list of tasks.
     */
    public List(int size) {
        this.listOfTasks = new Task[size];
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
     * Adds the command that is inputted by the user into the list of tasks.
     *
     * @param userInput Command entered by the user.
     */
    public void add(String userInput) {
        this.numberOfTasks++;

        Task currentTask = new Task(userInput);
        this.listOfTasks[numberOfTasks] = currentTask;

        String output = "    ____________________________________________________________\n"
                + "     added: " + userInput + "\n"
                + "    ____________________________________________________________\n";
        System.out.println(output);
    }

    /**
     * Iterates through the list and prints out each task that is on the list.
     */
    public void display() {
        System.out.println("    ____________________________________________________________\n"
                + "     Here are the tasks in your list:\n");
        for (int i = 1; i <= this.numberOfTasks; i++) {
            Task currTask = this.listOfTasks[i];
            String output = "     " + i + "." + currTask;
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
        Task currTask = this.listOfTasks[taskId];
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
        Task currTask = this.listOfTasks[taskId];
        currTask.setStatus(false);
        System.out.println("    ____________________________________________________________\n"
                + "     OK, I've marked this task as not done yet:\n"
                + "       [ ] " + currTask.getDescription() + "\n"
                + "    ____________________________________________________________\n");
    }
}
