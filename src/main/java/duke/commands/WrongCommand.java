package duke.commands;

import duke.main.DukeException;
import duke.main.Parser;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * WrongCommand is a Command.
 * This Command is used to indicate that a given Command does not exist.
 */
public class WrongCommand extends Command<String> {
    /**
     * Constructor for WrongCommand.
     * When this class is instantiated, it immediately throws a new DukeException.
     *
     * @throws DukeException when this class is instantiated
     */
    public WrongCommand() throws DukeException {
        Ui.setDukeResponseError(Parser.formatMsg("Sorry, I do not know what that means." +
                "\nDid you mean to type something else?"));
        throw new DukeException(Parser.formatMsg("OOPS!!! I'm sorry, but I don't know what that means :-("));
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
