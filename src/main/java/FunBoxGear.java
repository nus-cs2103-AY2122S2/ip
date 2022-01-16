/**
 * The FunBoxGear class contains all the functionalities of FunBox.
 */
public class FunBoxGear {
    public final String GREETING = "Yo! I am FunBox └[∵┌]└[ ∵ ]┘[┐∵]┘ \nWhat can I do for you?";
    private int noOfItems;
    private String[] itemsList;

    /**
     * Constructor for FunBoxGear
     */
    public FunBoxGear() {
        this.noOfItems = 0;
        this.itemsList = new String[100];
    }

    /**
     * Prints out the default GREETING on console
     */
    public void greet() {
        System.out.println(GREETING);
    }

    /**
     * Check if message written by the user is "bye"
     *
     * @param message The message written by the users on the command prompt
     * @return return true if the message is a "bye" otherwise return false
     */
    public boolean isBye(String message) {
        if (message.equals("bye")) {
            return true;
        }
        return false;
    }

    /**
     * Check if message written by the user is "list"
     *
     * @param message The message written by the users on the command prompt
     * @return return true if the message is a "bye" otherwise return false
     */
    public boolean isList(String message) {
        if (message.equals("list")) {
            return true;
        }
        return false;
    }

    /**
     * Add user's message to a list
     *
     * @param message The message written by the users on the command prompt
     */
    public void addToList(String message) {
        this.itemsList[noOfItems] = message;
        noOfItems++;
        System.out.println("added: " + message);
    }

    /**
     * Loop through a list of items and print out each item
     */
    public void showList() {
        for (int i = 0; i < this.noOfItems; i++) {
            System.out.println((i + 1) + ". " + this.itemsList[i]);
        }
    }

    /**
     * Print out goodbye message to the user
     */
    public void sayBye() {
        System.out.println("B-b-bbye. Hope to see you again soon ┗|*´Д｀|┛");
    }
}
