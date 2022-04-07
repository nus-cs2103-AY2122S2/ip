package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;

/**
 * Represents the parser for program to parse input.
 */
public class Parser {

    private String userInputLine;

    /**
     * Creates new Parser.
     *
     * @param userInputLine UserInputLine given for parser to parse.
     */
    public Parser(String userInputLine) {
        this.userInputLine = userInputLine;
    }

    /**
     * Returns Command that user wants to execute.
     *
     * @return Command to be executed on a TaskList.
     * @throws DukeException If userInputLine invalid for the command.
     */
    public Command parse() throws DukeException {
        String[] inputStrings = this.userInputLine.trim().split(" ", 2);
        String inputCommand = inputStrings[0];
        String inputBody = inputStrings.length == 2 ? inputStrings[1] : "";
        switch (inputCommand) {
        case "list":
            return new ListCommand("");
        case "deadline":
            return new DeadlineCommand(inputBody);
        case "todo":
            return new TodoCommand(inputBody);
        case "event":
            return new EventCommand(inputBody);
        case "mark":
            return new MarkCommand(inputBody);
        case "unmark":
            return new UnmarkCommand(inputBody);
        case "delete":
            return new DeleteCommand(inputBody);
        case "find":
            return new FindCommand(inputBody);
        case "bye":
            return new ByeCommand(inputBody);
        case "update":
            return new UpdateCommand(inputBody);
        default:
            throw new InvalidCommandException("OOPS!!! I'm sorry, but i don't know what that means :-(");
        }
    }

}
