package taskmaster;

import taskmaster.userinterface.UserInterface;

/**
 * This class encapsulates the main program, Taskmaster.
 */

public class Taskmaster {
    /** The user interface that allows the user to communicate with Taskmaster. **/
    protected UserInterface ui;

    /**
     * Constructor for Taskmaster the chatbot.
     */

    public Taskmaster () {
        this.ui = new UserInterface();
    }

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
