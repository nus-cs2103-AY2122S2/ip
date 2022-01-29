package duke.parser;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.command.*;

public class Parser {

    private String command;

    public Parser() {
    }

    public static Command<String> parseCommand(String command, TaskList list, Storage storage, Ui ui) throws DukeException {
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
        } else if (command.startsWith("find")) {
            return new FindCommand(command, list);
        } else {
            throw new DukeException("Oh dear, there must be an error somewhere!");
        }
    }
}
