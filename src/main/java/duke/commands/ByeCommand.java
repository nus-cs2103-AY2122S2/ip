package duke.commands;

import duke.main.TaskList;
import javafx.application.Platform;


/**
 * WrongCommand is a Command.
 * This Command is used to indicate that a given Command does not exist.
 */
public class ByeCommand extends Command<String> {
    /**
     * Constructor for WrongCommand.
     * When this class is instantiated, it immediately closes the GUI application
     *
     */
    public ByeCommand() {
        Platform.exit();
    }

    /**
     * Unused
     *
     * @param todoList the user's list of Tasks
     * @param cmd      the user input to Burp
     */
    public void runCommand(TaskList todoList, String cmd) {
    }
}
