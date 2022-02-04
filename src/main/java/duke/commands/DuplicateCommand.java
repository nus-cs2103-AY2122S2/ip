package duke.commands;

import duke.main.Parser;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * WrongCommand is a Command.
 * This Command is used to indicate that a given Command does not exist.
 */
public class DuplicateCommand extends Command<String> {
    /**
     * Constructor for DuplicateCommand.
     * When this class is instantiated, it simply sets the response to tell the user
     * that the item they were trying to add is a duplicate item
     */
    public DuplicateCommand() {
        Ui.setDukeResponse(Parser.formatMsg("This item is already in your list"));
    }

    /**
     * Unused
     *
     * @param toDoList the user's list of Tasks
     * @param cmd      the user input to Burp
     */
    public void runCommand(TaskList toDoList, String cmd) {
    }
}
