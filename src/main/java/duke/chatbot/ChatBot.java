package duke.chatbot;

import duke.data.TaskList;
import duke.chatbot.command.Command;
import duke.chatbot.command.ExitCommand;

import java.util.ArrayList;

/**
 * ChatBot class that serves as an interface between
 * the JavaFX GUI application and execution of user
 * inputted commands.
 */
public class ChatBot {

    /** Task list maintaining list of tasks for user */
    private final TaskList taskList;

    /** Boolean to track if bot has received a termination command */
    private boolean hasTerminated;

    public ChatBot(TaskList taskList) {
        this.taskList = taskList;
        this.hasTerminated = false;
    }

    /**
     * Runs the command given by user input string, and returns
     * a response from the ChatBot for the command, in the form
     * of an ArrayList of string.
     *
     * @param input String command by user to run.
     * @return ArrayList of string containing response of command ran.
     */
    public ArrayList<String> runCommand(String input) {
        try {
            Command command = Command.parseCommand(input, this.taskList);
            ArrayList<String> response = command.execute();
            this.hasTerminated = command instanceof ExitCommand;
            return response;
        } catch (IllegalArgumentException e) {
            ArrayList<String> response = new ArrayList<>();
            response.add("Sorry, the following problem has occurred:");
            response.add(e.getMessage());
            return response;
        }
    }

    public boolean hasTerminated() {
        return this.hasTerminated;
    }
}
