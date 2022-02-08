package duke.parser;

import duke.DukeException;
import duke.commands.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a parser to extract information from user input.
 */
public class Parser {

    /**
     * Parses user input into commands.
     *
     * @param input The user input string.
     * @return The command corresponding to the input.
     * @throws DukeException If the input string have errors in its format.
     */
    public static Command parse(String input) throws DukeException {
        String[] arguments = input.trim().split(" ", 2);

        switch (arguments[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return extractMark(arguments);
        case "unmark":
            return extractUnmark(arguments);
        case "todo":
            return extractToDo(arguments);
        case "deadline":
            return extractDeadline(arguments);
        case "event":
            return extractEvent(arguments);
        case "delete":
            return extractDelete(arguments);
        case "find":
            return extractFind(arguments);
        default:
            return new UnknownCommand();
        }
    }

    private static Command extractToDo(String[] arguments) {
        try {
            return new AddCommand(new ToDo(arguments[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing details! Please use the format: todo <description>", e);
        }
    }

    private static Command extractDeadline(String[] arguments) {
        try {
            String[] taskDetails = arguments[1].split(" /by ");
            LocalDateTime dueDate = LocalDateTime.parse(taskDetails[1], DateTimeFormatter.ofPattern("d/M/y HHmm"));
            return new AddCommand(new Deadline(taskDetails[0], dueDate));
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException(
                    "Missing details! Please use the format: deadline <description> /by <dd/mm/yyyy hhmm>", e);
        }
    }

    private static Command extractEvent(String[] arguments) {
        try {
            String[] taskDetails = arguments[1].split(" /at ");
            LocalDateTime eventDate = LocalDateTime.parse(taskDetails[1], DateTimeFormatter.ofPattern("d/M/y HHmm"));
            return new AddCommand(new Event(taskDetails[0], eventDate));
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DukeException(
                    "Missing details! Please use the format: event <description> /at <dd/mm/yyyy hhmm>", e);
        }
    }

    private static Command extractMark(String[] arguments) {
        try {
            return new MarkCommand(Integer.parseInt(arguments[1].trim()) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an integer!", e);
        }
    }

    private static Command extractUnmark(String[] arguments) {
        try {
            return new UnmarkCommand(Integer.parseInt(arguments[1].trim()) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an integer!", e);
        }
    }

    private static Command extractDelete(String[] arguments) {
        try {
            return new DeleteCommand(Integer.parseInt(arguments[1].trim()) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an integer!", e);
        }
    }

    private static Command extractFind(String[] arguments) {
        try {
            return new FindCommand(arguments[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Enter some words to search!", e);
        }
    }
}
