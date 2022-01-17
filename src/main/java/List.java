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
        System.out.println("    ____________________________________________________________\n");
        for (int i = 1; i <= this.numberOfTasks; i++) {
            String output = "     " + i + ". " + this.listOfTasks[i].getDescription() + "\n";
            System.out.println(output);
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
