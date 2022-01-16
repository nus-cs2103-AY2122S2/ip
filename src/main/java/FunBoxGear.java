/**
 * The FunBoxGear class contains all the functionalities of FunBox.
 */
public class FunBoxGear {
    public final String GREETING = "Yo! I am FunBox └[∵┌]└[ ∵ ]┘[┐∵]┘ \nWhat can I do for you?";
    private int noOfItems;
    private Task[] tasksList;

    /**
     * Constructor for FunBoxGear
     */
    public FunBoxGear() {
        this.noOfItems = 0;
        this.tasksList = new Task[100];
    }

    /**
     * Prints out the default GREETING on console
     */
    public void greet() {
        System.out.println(GREETING);
    }

    /**
     * Check whether if user's input is a commands
     *
     * @param message The user's input to the command prompt
     * @return Return false if message is "bye", otherwise return true
     */
    public boolean getCommands(String message) {
        String[] formattedMsg = this.formatCommands(message);
        switch (formattedMsg[0]) {
        case "bye":
            this.sayBye();
            return false;
        case "list":
            this.showList();
            return true;
        case "mark":
            this.markDone(formattedMsg);
            return true;
        case "unmark":
            this.markUndone(formattedMsg);
            return true;
        default:
            this.addToList(message);
            return true;
        }
    }

    /**
     * Add user's message to a list
     *
     * @param message The message written by the users on the command prompt
     */
    private void addToList(String message) {
        this.tasksList[noOfItems] = new Task(message);
        noOfItems++;
        System.out.println("added: " + message);
    }

    /**
     * Loop through a list of items and print out each item
     */
    private void showList() {
        System.out.println("Here are the tasks in your list \uD83D\uDCF3:");
        for (int i = 0; i < this.noOfItems; i++) {
            System.out.println((i + 1) + "." + this.tasksList[i]);
        }
    }

    /**
     * Format the user's message to be able to differentiate between special commands
     *
     * @param message The user's message to be formatted
     * @return Return a String array which contains the split message. The first element is used to differentiate
     * whether it's a message, command, or command which require special formatting
     */
    private String[] formatCommands(String message) {
        // Split message by blank space
        String[] messageArr = message.split(" ");
        return messageArr;
    }

    /**
     * Mark the item on the list as done
     *
     * @param messageArr The formatted message of the user, the second item of the array typically contains the
     *                   taskNo to be mark as done
     */
    private void markDone(String[] messageArr) {
        int taskNo = Integer.parseInt(messageArr[1]);
        this.tasksList[taskNo - 1].setDone();
    }

    /**
     * Mark the item on the list as not done
     *
     * @param messageArr The formatted message of the user, the second item of the array typically contains the
     *                   taskNo to be mark as not done
     */
    private void markUndone(String[] messageArr) {
        int taskNo = Integer.parseInt(messageArr[1]);
        this.tasksList[taskNo - 1].setUndone();
    }

    /**
     * Print out goodbye message to the user
     */
    public void sayBye() {
        System.out.println("B-b-bbye. Hope to see you again soon ┗|*´Д｀|┛");
    }
}
