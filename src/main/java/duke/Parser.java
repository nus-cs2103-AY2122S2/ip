package duke;

import duke.command.*;
import duke.exception.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

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
            // check date here
            try {
                String[] d_deets = details.split("/");
                String givenDate = d_deets[1].trim().substring(3); //ignore the words at or by + the space that follows
                LocalDate parsedDate = LocalDate.parse(givenDate);
                return new AddCommand(cmd, d_deets[0].trim(), parsedDate);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter a date with the format yyyy-mm-dd");
            }
        case "find":
            return new FindCommand(details);
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
