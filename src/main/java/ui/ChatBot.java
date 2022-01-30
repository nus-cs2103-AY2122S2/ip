package ui;

import data.TaskList;
import ui.command.Command;

import java.util.ArrayList;

/**
 * ChatBot class that allows for running of user command
 * and keeping track of its termination status.
 */
public class ChatBot {

    private static final String WELCOME_STRING = "Hello I'm Duke!\n"
            + "What can I do for you?";

    /** Task list maintaining list of tasks for user */
    private final TaskList taskList;

    /** Boolean to track if bot has received a termination command */
    private boolean hasTerminated;

    public ChatBot(TaskList taskList) {
        this.taskList = taskList;
        this.hasTerminated = false;
    }


    /**
     * Performs initialisation of the ChatBot. Should be called
     * before receiving commands.
     */
    public void initialise() {
        System.out.println(WELCOME_STRING);
    }

    /**
     * Runs the command given by user input string.
     *
     * @param input String command by user to run.
     */
    public void runCommand(String input) {
        try {
            Command command = Command.parseCommand(input, this.taskList);
            this.hasTerminated = command.execute();
        } catch (IllegalArgumentException e) {
            ArrayList<String> response = new ArrayList<>();
            response.add("Sorry, the following problem has occurred:");
            response.add(e.getMessage());
            Command.styledPrint(response);
        }
    }

    public boolean hasTerminated() {
        return this.hasTerminated;
    }
}
