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
        String[] details = input.split(" ", 2);

        switch (details[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return extractMark(details);
        case "unmark":
            return extractUnmark(details);
        case "todo":
            return extractToDo(details);
        case "deadline":
            return extractDeadline(details);
        case "event":
            return extractEvent(details);
        case "delete":
            return extractDelete(details);
        case "find":
            return extractFind(details);
        default:
            return new UnknownCommand();
        }
    }

    private static Command extractToDo(String[] details) {
        try {
            if (details[1].trim().length() == 0) {
                throw new IndexOutOfBoundsException("Empty description string");
            }
            return new AddCommand(new ToDo(details[1]));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Missing details! Please use the format: todo <description>", e);
        }
    }

    private static Command extractDeadline(String[] details) {
        try {
            String[] args = details[1].split(" /by ");
            LocalDateTime by = LocalDateTime.parse(args[1], DateTimeFormatter.ofPattern("d/M/y HHmm"));
            return new AddCommand(new Deadline(args[0], by));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "Missing details! Please use the format: deadline <description> /by <dd/mm/yyyy hhmm>", e);
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "Incorrect date/time format! Please use <dd/mm/yyyy hhmm>", e);
        }
    }

    private static Command extractEvent(String[] details) {
        try {
            String[] args = details[1].split(" /at ");
            LocalDateTime at = LocalDateTime.parse(args[1], DateTimeFormatter.ofPattern("d/M/y HHmm"));
            return new AddCommand(new Event(args[0], at));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(
                    "Missing details! Please use the format: event <description> /at <dd/mm/yyyy hhmm>", e);
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "Incorrect date/time format! Please use <dd/mm/yyyy hhmm>", e);
        }
    }

    private static Command extractMark(String[] details) {
        try {
            return new MarkCommand(Integer.parseInt(details[1].trim()) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an integer!", e);
        }
    }

    private static Command extractUnmark(String[] details) {
        try {
            return new UnmarkCommand(Integer.parseInt(details[1].trim()) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an integer!", e);
        }
    }

    private static Command extractDelete(String[] details) {
        try {
            return new DeleteCommand(Integer.parseInt(details[1].trim()) - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Please enter an integer!", e);
        }
    }

    private static Command extractFind(String[] details) {
        try {
            return new FindCommand(details[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Enter some word to search!", e);
        }
    }
}
