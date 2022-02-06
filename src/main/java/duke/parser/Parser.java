package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.SearchCommand;
import duke.commands.ToggleCommand;
import duke.common.DukeException;
import duke.constants.Constants;

/**
 * Parses or processes commands given by user input.
 */
public class Parser {
    private String fullCommand;

    /**
     * Creates a new Parser object to be used throughout the lifetime of the program.
     * 
     * @param fullCommand fullCommand is the entire string of the user input.
     */
    public Parser(String fullCommand) {
        assert fullCommand != null : "Parser[Parser] fullCommand cannot be null.";
        assert fullCommand.length() > 0 : "Parser[Parser] fullCommand must contain data.";

        this.fullCommand = fullCommand;
    }

    /**
     * Parses or processes commands given by user input. Also, processing the arguments if given.
     * 
     * @return The individual command that the user has input.
     * @throws DukeException If the command is not known to Abby.
     */
    public Command parse() throws DukeException {
        String[] inputArr = this.fullCommand.split(" ", 2);
        String command = inputArr[0];
        String args = (inputArr.length > 1) ? inputArr[1] : "";

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
        } else if (command.equalsIgnoreCase("find")) {
            return new FindCommand(args);
        } else {
            throw new DukeException(Constants.UNKNOWN_MSG);
        }
    }

    private boolean isAdd(String command) {
        assert command != null : "Parser[isAdd] command cannot be null.";
        assert command.length() > 0 : "Parser[isAdd] command must contain data.";

        return command.equalsIgnoreCase("todo")
                || command.equalsIgnoreCase("deadline")
                || command.equalsIgnoreCase("event");
    }
}
