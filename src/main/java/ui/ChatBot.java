package ui;

import ui.command.*;

/**
 * @author Jiaaa-yang
 *
 * ChatBot class that allows for running of user command
 * and keeping track of its termination status.
 */
public class ChatBot {
    private final static String WELCOME_STRING = "Hello I'm Duke!\n"
                                                + "What can I do for you?";

    /**
     * Boolean to track if bot has received a termination command
     */
    private boolean terminated;

    public ChatBot() {
        this.terminated = false;
    }

    /**
     * Performs initialisation of the ChatBot. Should be called
     * before receiving commands
     */
    public void initialise() {
        System.out.println(WELCOME_STRING);
    }

    /**
     * Runs the command given by user input string
     *
     * @param input String command by user to run
     */
    public void runCommand(String input) {
        Command command;
        switch (input) {
            case "bye":
                command = new ExitCommand(input);
                break;
            default:
                command = new EchoCommand(input);
        }
        this.terminated = command.execute();
    }

    public boolean isTerminated() {
        return this.terminated;
    }
}
