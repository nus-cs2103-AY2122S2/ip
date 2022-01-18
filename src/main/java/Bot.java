/**
 * Bot that is the driver for the responses to the user.
 * Internally, Bot has a Storage, which is used whenever an input is given by the user.
 * 1st method: add, which adds inputs by the user into the storage.
 * 2nd method: list, which shows what's in the storage currently.
 */

import java.util.ArrayList;

public class Bot {
    Storage tasks;
    /**
     * Constructs a new Bot containing a Storage
     */
    Bot() {
        this.tasks = new Storage();
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
        if (input.equals("list")) {
            tasks.list();
            return false;
        } else if (input.equals("bye")) {
            leave();
            return true;
        } else if (input.equals("")) {
            System.out.println("Say something!");
            return false;
        } else {
            tasks.add(input);
            return false;
        }
    }
}
