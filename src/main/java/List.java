public class List {
    private final String[] listOfTasks;
    private int numberOfTasks;

    /**
     * Initializes a new instance of list of tasks, according to the size specified.
     *
     * @param size Size of list of tasks.
     */
    public List(int size) {
        this.listOfTasks = new String[size];
        this.numberOfTasks = 0;
    }

    /**
     * Adds the command that is inputted by the user into the list of tasks.
     *
     * @param userInput Command entered by the user.
     */
    public void add(String userInput) {
        this.numberOfTasks++;
        this.listOfTasks[numberOfTasks] = userInput;

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
            String output = "     " + i + ". " + this.listOfTasks[i] + "\n";
            System.out.println(output);
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
