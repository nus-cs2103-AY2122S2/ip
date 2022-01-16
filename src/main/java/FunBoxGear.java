/**
 * The FunBoxGear class contains all the functionalities of FunBox.
 */
public class FunBoxGear {
    public final String GREETING = "Yo! I am FunBox └[∵┌]└[ ∵ ]┘[┐∵]┘ \nWhat can I do for you?";

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
     * Echo user's message and print it out on console
     *
     * @param message The message written by the users on the command prompt
     */
    public void echo(String message) {
        System.out.println(message);
    }

    /**
     * Print out goodbye message to the user
     */
    public void sayBye() {
        System.out.println("B-b-bbye. Hope to see you again soon ┗|*´Д｀|┛");
    }
}
