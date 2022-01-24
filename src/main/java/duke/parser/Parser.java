package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.SearchCommand;
import duke.commands.ToggleCommand;
import duke.common.DukeException;
import duke.constants.Constants;

public class Parser {
    private String fullCommand;

    public Parser(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public Command parse() throws DukeException {
        String[] inputArr = this.fullCommand.split(" ", 2);
        String command = inputArr[0];
        String args = inputArr.length > 1 ? inputArr[1] : "";

        if (command.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        } else if (command.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (command.equalsIgnoreCase("mark")) {
            return new ToggleCommand(args, true);
        } else if (command.equalsIgnoreCase("unmark")) {
            return new ToggleCommand(args, false);
        } else if (command.equalsIgnoreCase("delete")) {
            return new DeleteCommand(args);
        } else if (command.equalsIgnoreCase("search")) {
            return new SearchCommand(args);
        } else if (isAdd(command)) {
            return new AddCommand(fullCommand);
        } else {
            throw new DukeException(Constants.UNKNOWN_MSG);
        }
    }

    private boolean isAdd(String command) {
        return command.equalsIgnoreCase("todo") ||
                command.equalsIgnoreCase("deadline") ||
                command.equalsIgnoreCase("event");
    }
}
