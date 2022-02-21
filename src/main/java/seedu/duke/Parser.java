package seedu.duke;

import seedu.command.*;
import seedu.exception.DukeException;

/**
 * Contains methods to parse user input to be understood based on the
 * type of command made by user.
 * Provides method to get the length (in terms of words separated by " ")
 * of user input.
 */
public class Parser {
    private String inputCommand;

    public Parser(String inputCommand) {
        assert inputCommand != null : "Parser->Parser: Input command string cannot be null.";

        this.inputCommand = inputCommand.toLowerCase();
    }

    /**
     * Parses the user input to return the first word to determine the type of command.
     *
     * @return Returns the first word which is the command type of user input.
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
     * Parses the user input command to retrieve
     * the command action.
     *
     * @return Returns a String of the command action.
     */
    public String getCommandAction() {
        return inputCommand.split(" ", 2)[0];
    }

    /**
     * Parses the user input command for a deadline task to retrieve
     * the description and date.
     *
     * @return Returns a String of the description.
     */
    public String getCommandDetails() throws DukeException{
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
