/**
 * Bot that is the driver for the responses to the user. Bot supports 2 methods
 * greet, when the program just starts, and reply when users key an input.
 */

public class Bot {
    void greet() {
        System.out.println("Hello! I'm Bernie\nWhat's up?");
    }

    /**
     * Reads the input and outputs a message based on the input
     *
     * @param input String input by the user
     * @return Formatted messaged based on the user's input
     */
    boolean reply(String input) {
        if (input.equals("bye")) {
            System.out.println("See ya!\n");
            return true;
        } else if (input.equals("")) {
            System.out.println("Say something!\n");
            return false;
        } else {
            System.out.println(input + "\n");
            return false;
        }
    }
}
