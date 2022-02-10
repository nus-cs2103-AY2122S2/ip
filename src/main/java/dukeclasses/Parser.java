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
     * @return ParsedCommand that denotes the command and other relevant information if the
     *         user input is valid.
     * @throws DukeException If user input is invalid.
     */
    public static ParsedCommand parse(String userCommand, Integer sizeOfTaskList) throws DukeException {
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
            if (parsedCommand.length <= 1) {
                throw new DukeException();
            }
            ParsedCommand fullyParsedCommand = parseIntegerInput(
                    parsedCommand[0], parsedCommand[1].trim(), sizeOfTaskList);
            if (fullyParsedCommand == null) {
                throw new DukeException();
            }
            return fullyParsedCommand;
        case "find":
        case "todo":
            return new ParsedCommand(parsedCommand[0], parsedCommand[1]);
        case "event":
        case "deadline":
            if (parsedCommand[1].contains("/by")) {
                String[] inputForConstructorWithDate = parsedCommand[1].split("/by", 2);
                ParsedCommand processedCommand =  parseDateInput(parsedCommand[0].trim(),
                        inputForConstructorWithDate[1].trim());
                if (processedCommand == null) {
                    throw new DukeException();
                }
                return processedCommand;
            } else {
                throw new DukeException();
            }
        default:
            throw new DukeException();
        }
    }

    /**
     * Parse input where an integer is expected.
     *
     * @param command String that contains the command word
     * @param index String that contains the index
     * @param lengthOfTaskList Length of the arrayList supplied.
     * @return ParsedCommand with the right command and input, if not return null.
     */
    private static ParsedCommand parseIntegerInput(String command, String index, Integer lengthOfTaskList) {
        try {
            Integer taskIndex = Integer.parseInt(index) - 1;
            if (taskIndex >= 0 && taskIndex < lengthOfTaskList) {
                return new ParsedCommand(command, taskIndex);
            } else {
                return null;
            }
        } catch (NumberFormatException error) {
            return null;
        }
    }

    /**
     * Parses input with a LocalDate expected.
     *
     * @param command String with the command
     * @param stringWithDate String that contains the date to be parsed.
     * @return ParsedCommand with the right command and date, else a null is returned.
     */
    private static ParsedCommand parseDateInput(String command, String stringWithDate) {
        try {
            LocalDate dueDate = LocalDate.parse(stringWithDate);
            return new ParsedCommand(command, stringWithDate, dueDate);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
