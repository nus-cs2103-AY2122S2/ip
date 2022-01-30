package duke.Parser;

import duke.Command.*;
import duke.DukeExceptions.DukeException;

public class Parser {

    public static Command parseCommand(String input) throws DukeException {
        String[] parts = input.split(" ");
        switch (parts[0]) {
        case "bye":
            return new ByeCommand(input);
        case "list":
            return new ListCommand(input);
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "todo":
            return new TodoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
