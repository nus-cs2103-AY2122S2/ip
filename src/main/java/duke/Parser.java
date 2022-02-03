package duke;

import duke.command.*;
import duke.exception.DukeException;

/**
 * Parser deals with understanding the user's input and create the correspondign Command to deal with the request.
 */
public class Parser {

    /**
     * Parses a single line from the user input that is broken up into 2 strings. The first string is the first
     * command word, and the second string is the details of the command. From here, the command word is checked to
     * decide on whether to respond with an appropriate command, or an exception if the command is not recognized.
     * @param cmd the first word in a line from the user's input
     * @param details the subsequent words in the same line from the user
     * @return an appropriate Command based on the user's request
     * @throws DukeException
     */
    public static Command parse(String cmd, String details) throws DukeException {
        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(details, true);
        case "unmark":
            return new MarkCommand(details, false);
        case "delete":
            return new DeleteCommand(details);
        case "todo":
            return new AddCommand(details);
        case "deadline":
        case "event":
            return new AddCommand(cmd, details);
        case "find":
            return new FindCommand(details);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


}
