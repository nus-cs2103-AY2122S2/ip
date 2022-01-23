package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.ErrorMessage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDate;

// deals with making sense of the user command
public class Parser {
    public static Command parseCommand(String input) throws DukeException {
        if (input.isBlank()) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_INPUT);
        }
        String command = input.split(" ")[0];
        String[] args = input.split(" ", 2);
        switch (command) {
        case "list":
            return new ListCommand();
        case "delete":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_INDEX);
            }
            return new DeleteCommand(Integer.parseInt(args[1]));
        case "mark":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_INDEX);
            }
            return new MarkCommand(Integer.parseInt(args[1]));
        case "unmark":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_INDEX);
            }
            return new UnmarkCommand(Integer.parseInt(args[1]));
        case "deadline":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
            }
            String[] deadline = args[1].split("/by", 2);
            if (deadline.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DATE);
            }
            return new AddCommand(new Deadline(deadline[0], parseDate(deadline[1])));
        case "event":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
            }
            String[] event = args[1].split("/at", 2);
            if (event.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DATE);
            }
            return new AddCommand(new Event(event[0], parseDate(event[1])));
        case "todo":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_DESC);
            }
            return new AddCommand(new Todo(args[1]));
        case "find":
            if (args.length == 1) {
                throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_KEYWORD);
            }
            return new FindCommand(args[1]);
        case "bye":
            return new ExitCommand();
        default:
            throw new DukeException(ErrorMessage.MESSAGE_UNKNOWN_COMMAND);
        }
    }

    public static LocalDate parseDate(String date) throws DukeException {
        String[] splitDate = date.trim().split("-"); // yyyy-mm-dd
        if (splitDate.length != 3) {
            throw new DukeException(ErrorMessage.MESSAGE_INVALID_DATE_FORMAT);
        }
        return LocalDate.of(Integer.parseInt(splitDate[0]),
                Integer.parseInt(splitDate[1]), Integer.parseInt(splitDate[2]));
    }
}
