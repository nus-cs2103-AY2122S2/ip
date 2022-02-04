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
     * @param input the input line from the user
     * @return an appropriate Command based on the user's request
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ", 2);
        String cmd = inputArr[0];
        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(inputArr[1], true);
        case "unmark":
            return new MarkCommand(inputArr[1], false);
        case "delete":
            return new DeleteCommand(inputArr[1]);
        case "todo":
            return new AddCommand(inputArr[1]);
        case "deadline":
        case "event":
            return new AddCommand(cmd, inputArr[1]);
        case "find":
            return new FindCommand(inputArr[1]);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }


}
