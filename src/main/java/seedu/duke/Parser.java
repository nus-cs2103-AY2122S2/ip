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
        String commandDetails = this.getCommandDetails();

        switch (commandAction) {
        case "hi":
            return new WelcomeCommand();
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListTasksCommand();
        case "todo":
            return new AddToDoCommand(commandDetails);
        case "event":
            return new AddEventCommand(commandDetails);
        case "deadline":
            return new AddDeadlineCommand(commandDetails);
        case "mark":
            return new MarkTaskCommand(commandDetails);
        case "unmark":
            return new UnmarkTaskCommand(commandDetails);
        case "delete":
            return new DeleteTaskCommand(commandDetails);
        case "find":
            return new FindTasksCommand(commandDetails);
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
    public String getCommandDetails() {
        try {
            return inputCommand.split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }
}
