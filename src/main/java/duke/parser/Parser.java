package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Parser class
 */
public class Parser {

    /**
     * Constructor for parser object
     */
    public Parser() {
    }

    /**
     * Parse the command and select appropriate command
     * @param command input command
     * @param taskList task list that the command will act on
     * @param storage storage where command will store result
     * @param ui ui to interact with user
     * @return parsed command
     * @throws DukeException if input is not a command
     */
    public static Command<String> parseCommand(String command, TaskList taskList, Storage storage, Ui ui)
            throws DukeException {
        if ((command.replaceAll("\\s+", "")).equals("bye")) {
            return new ByeCommand();
        } else if ((command.replaceAll("\\s+", "")).equals("list")) {
            return new ListCommand(taskList);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(command, taskList, storage);
        } else if (command.startsWith("todo")) {
            return new TodoCommand(command, taskList, storage, ui);
        } else if (command.startsWith("deadline")) {
            return new DeadlineCommand(command, taskList, storage, ui);
        } else if (command.startsWith("event")) {
            return new EventCommand(command, taskList, storage, ui);
        } else if (command.startsWith("unmark")) {
            return new UnmarkCommand(command, taskList, storage);
        } else if (command.startsWith("mark")) {
            return new MarkCommand(command, taskList, storage);
        } else if (command.startsWith("find")) {
            return new FindCommand(command, taskList);
        } else {
            throw new DukeException("Oh dear, there must be an error somewhere!");
        }
    }
}
