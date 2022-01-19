/**
 * Bot that is the driver for the responses to the user.
 * Internally, Bot has a Storage, which is used whenever an input is given by the user.
 * 1st method: add, which adds inputs by the user into the storage.
 * 2nd method: list, which shows what's in the storage currently.
 */

public class Bot {
    TaskList tasks;
    /**
     * Constructs a new Bot containing a Storage
     */
    Bot() {
        this.tasks = new TaskList();
    }
    void greet() {
        System.out.println("Hello! I'm Bernie\nWhat's up?");
    }
    void leave() {
        System.out.println("See ya!");
    }
    /**
     * Displays to the user a message according to the input given
     * @param input String, takes in an input and either adds or list the items
     * @return a boolean to indicate whether to end the program
     */
    boolean respond(String input) {
        try {
            if (input.equals("list")) {
                tasks.list();
            } else if (input.equals("bye")) {
                leave();
            } else if (input.contains("mark") || input.contains("unmark")) {
                tasks.mark(input);
            } else if (input.equals("")) {
                throw new IllegalArgumentException("No input given!");
            } else {
                tasks.add(input);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            return input.equals("bye");
        }
    }
}
