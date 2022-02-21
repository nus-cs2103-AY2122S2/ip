package seedu.duke;

import seedu.command.AddDeadlineCommand;
import seedu.command.AddEventCommand;
import seedu.command.AddToDoCommand;
import seedu.command.Command;
import seedu.command.DeleteTaskCommand;
import seedu.command.ExitCommand;
import seedu.command.FindTasksCommand;
import seedu.command.ListTasksCommand;
import seedu.command.MarkTaskCommand;
import seedu.command.UnmarkTaskCommand;
import seedu.command.WelcomeCommand;
import seedu.exception.DukeException;

/**
 * Contains methods to parse user input to be understood based on the type of command made by user
 * and create new Command subclass object instances accordingly.
 */
public class Parser {
    private final String inputCommand;

    public Parser(String inputCommand) {
        assert inputCommand != null : "Parser->Parser: Input command string cannot be null.";

        this.inputCommand = inputCommand.toLowerCase();
    }

    /**
     * Parses the user input to return the new command object created with the command details (if
     * applicable).
     *
     * @return Returns the new command object created based on the command type determined.
     * @throws DukeException if command action is not recognisable.
     */
    public Command getCommand() throws DukeException {
        String commandAction = this.getCommandAction();

        switch (commandAction) {
        case "hi":
            return new WelcomeCommand();
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListTasksCommand();
        case "todo":
            return new AddToDoCommand(getCommandDetails());
        case "event":
            return new AddEventCommand(getCommandDetails());
        case "deadline":
            return new AddDeadlineCommand(getCommandDetails());
        case "mark":
            return new MarkTaskCommand(getCommandDetails());
        case "unmark":
            return new UnmarkTaskCommand(getCommandDetails());
        case "delete":
            return new DeleteTaskCommand(getCommandDetails());
        case "find":
            return new FindTasksCommand(getCommandDetails());
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :(");
        }
    }

    /**
     * Parses the user input command to retrieve the command action.
     *
     * @return A string of the command action.
     */
    public String getCommandAction() {
        return inputCommand.split(" ", 2)[0];
    }

    /**
     * Parses the user input command to retrieve the details (if applicable).
     *
     * @return A String of the command details.
     * @throws DukeException If no details are provided where needed.
     */
    public String getCommandDetails() throws DukeException {
        try {
            String commandDetails = inputCommand.split(" ", 2)[1];
            if (commandDetails.length() == 0) {
                throw new DukeException("You did not provide any details for the command :( \n Please try again!");
            } else {
                return commandDetails;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("You did not provide any details for the command :( \n Please try again!");
        }
    }
}
