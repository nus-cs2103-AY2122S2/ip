package duke.parser;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.command.*;

/**
 * Parser class
 */
public class Parser {

    private String command;

    /**
     * Constructor for parser object
     */
    public Parser() {
    }

    /**
     * Parse the command and select appropriate command
     * @param command input command
     * @param list task list that the command will act on
     * @param storage storage where command will store result
     * @param ui ui to interact with user
     * @return parsed command
     * @throws DukeException if input is not a command
     */
    public static Command<String> parseCommand(String command, TaskList list, Storage storage, Ui ui)
            throws DukeException {
        if ((command.replaceAll("\\s+", "")).equals("bye")) {
            return new ByeCommand();
        } else if ((command.replaceAll("\\s+", "")).equals("list")) {
            return new ListCommand(list);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command, list, storage);
        } else if (command.startsWith("todo")) {
            return new TodoCommand(command, list, storage, ui);
        } else if (command.startsWith("deadline")) {
            return new DeadlineCommand(command, list, storage, ui);
        } else if (command.startsWith("event")) {
            return new EventCommand(command, list, storage,ui);
        } else if (command.startsWith("unmark")) {
            return new UnmarkCommand(command, list, storage);
        } else if (command.startsWith("mark")) {
            return new MarkCommand(command, list, storage);
        } else {
            throw new DukeException("Oh dear, there must be an error somewhere!");
        }
    }
}
