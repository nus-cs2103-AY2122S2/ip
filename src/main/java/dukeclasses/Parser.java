package dukeclasses;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a Parser that parses user input to make sense of what the user inputs.
 */
public class Parser {

    /**
     * Parses the user input.
     *
     * @param userCommand String that the user inputs.
     * @return ParsedCommand that denotes the command and other relevant information if the user input is valid.
     * @throws DukeException If user input is invalid.
     */
    public static ParsedCommand parse(String userCommand) throws DukeException {
        String[] parsedCommand = userCommand.split(" ", 2);
        parsedCommand[0] = parsedCommand[0].trim();
        switch (parsedCommand[0]) {
        case "hi":
        case "list":
        case "bye":
            return new ParsedCommand(parsedCommand[0]);
        case "mark":
        case "unmark":
        case "delete":
            Integer taskIndex = null;
            if (parsedCommand.length <= 1) {
                throw new DukeException();
            }
            try {
                taskIndex = Integer.parseInt(parsedCommand[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException();
            }

            if (taskIndex < 0) {
                throw new DukeException();
            }

            return new ParsedCommand(parsedCommand[0], taskIndex);
        case "find":
        case "todo":
            if (parsedCommand.length <= 1) {
                throw new DukeException();
            }
            return new ParsedCommand(parsedCommand[0], parsedCommand[1]);
        case "event":
        case "deadline":
            if (parsedCommand.length <= 1) {
                throw new DukeException();
            } else if (!parsedCommand[1].contains("/by")) {
                throw new DukeException();
            }

            String[] inputForConstructorWithDate = parsedCommand[1].split("/by", 2);
            LocalDate dueDate = null;
            try {
                dueDate = LocalDate.parse(inputForConstructorWithDate[1].trim());
            } catch (DateTimeParseException e) {
                throw new DukeException();
            }

            return new ParsedCommand(parsedCommand[0].trim(), inputForConstructorWithDate[0].trim(), dueDate);
        default:
            throw new DukeException();
        }
    }

}
