package spark;

import spark.commands.commandtypes.*;
import spark.commands.CommandKeyword;

/**
 * Holds methods for interpreting user's input.
 */
public class Parser {
    public static Command parseInput(String input) {
        String[] tokens = input.split(" "); // split command into individual keywords by single-space
        String commandKeyword = tokens[0]; // assume that the first keyword is always the command word
        CommandKeyword keyword = CommandKeyword.getCommand(commandKeyword);

        switch (keyword) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(tokens);
        case UNMARK:
            return new UnMarkCommand(tokens);
        case DELETE:
            return new DeleteTaskCommand(tokens);
        case TODO:
            return new AddToDoCommand(tokens);
        case DEADLINE:
            return new AddDeadlineCommand(tokens);
        case EVENT:
            return new AddEventCommand(tokens);
        case FIND:
            return new FindTaskCommand(tokens);
        default:
            return new UnrecognisedCommand();
        }
    }
}
