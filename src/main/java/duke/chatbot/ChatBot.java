package duke.chatbot;

import java.util.ArrayList;

import duke.chatbot.command.AddTaskCommand;
import duke.chatbot.command.Command;
import duke.chatbot.command.DeleteTaskCommand;
import duke.chatbot.command.ExitCommand;
import duke.chatbot.command.MarkTaskCommand;
import duke.data.TaskList;


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

    /** Last executed command by ChatBot */
    private Command lastExecutedCommand = null;

    /**
     * Returns a ChatBot that run commands based
     * on given TaskList.
     *
     * @param taskList TaskList instance keeping track of
     *                 list of tasks for user.
     */
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
            Command command = Command.parseCommand(input, this.taskList, this.lastExecutedCommand);
            ArrayList<String> response = command.execute();
            this.updateTerminationStatus(command);
            this.updateLastExecutedCommand(command);
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

    /**
     * Updates termination status of ChatBot based on last
     * command ran. Changes termination status to true if
     * command is exit command, false otherwise.
     *
     * @param command Last command ran by ChatBot.
     */
    private void updateTerminationStatus(Command command) {
        this.hasTerminated = command instanceof ExitCommand;
    }

    /**
     * Updates last executed command, only if the command
     * is a non-trivial one which changes the tasks.
     *
     * @param newCommand Command that was just ran.
     */
    private void updateLastExecutedCommand(Command newCommand) {
        //TODO: Use inheritance to group non-trivial commands
        if (newCommand instanceof AddTaskCommand
                || newCommand instanceof DeleteTaskCommand
                || newCommand instanceof MarkTaskCommand) {
            this.lastExecutedCommand = newCommand;
        }
    }
}
