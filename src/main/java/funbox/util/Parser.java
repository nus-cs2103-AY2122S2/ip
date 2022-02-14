package funbox.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import funbox.command.*;
import funbox.exception.FunBoxExceptions;

import javax.swing.text.html.HTML;

/**
 * Deal with parsing and making sense of the user commands.
 */
public class Parser {
    private final String dateFormat = "[dd/MM/yyyy][d/MM/yyyy][dd/M/yyyy][d/MM/yyyy]" +
            "[dd-MM-yyyy][d-MM-yyyy][dd-M-yyyy][d-MM-yyyy][yyyy-MM-dd]";

    /**
     * Parses the user text commands to a Command object.
     *
     * @param command The command entered by the user.
     * @return Return a command object to be executed.
     * @throws FunBoxExceptions If command is not available/exist.
     */
    public Command parseCommand(String command) throws FunBoxExceptions {
        String[] parsedCommand = command.split(" ");
        String commandType = parsedCommand[0];
        String description = parsedCommand.length > 1
                ? this.concatFormat(parsedCommand, 1) : "";
        switch (commandType) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ByeCommand();
        case "todo":
            return new ToDoCommand(description);
        case "event":
            return new EventCommand(description);
        case "deadline":
            return new DeadlineCommand(description);
        case "delete":
            return new DeleteCommand(description);
        case "mark":
            return new MarkCommand(description);
        case "unmark":
            return new UnmarkCommand(description);
        case "filter":
            return new FilterCommand(description);
        case "find":
            return new FindCommand(description);
        case "tag":
            return new TagCommand(description);
        default:
            throw new FunBoxExceptions("ERROR! I do not know what the commands means :<");
        }
    }

    /**
     * Concatenates the strings in the array to a single string.
     *
     * @param parsedCommand The array containing the strings to be concatenated.
     * @param startingIndex The point which the concatenation should begin.
     * @return Return a concatenated string.
     */
    public String concatFormat(String[] parsedCommand, int startingIndex) {
        String result = "";
        int arrayLength = parsedCommand.length;
        int lastIndex = arrayLength - 1;

        for (int i = startingIndex; i < arrayLength; i++) {
            if (i == lastIndex) {
                result = result.concat(parsedCommand[i]);
                break;
            }
            result = result.concat(parsedCommand[i] + " ");
        }
        return result;
    }

    /**
     * Gets the description and date from a string.
     *
     * @param message The message to be parsed to retrieve the description and date.
     * @param type The type of task.
     * @return Returns a string array where the first item is the description
     *         and the second item contains the date.
     */
    public String[] getDescAndDate(String message, String type) {
        if (type.equals("event")) {
            return message.split(" /at ");
        }
        return message.split(" /by ");
    }

    /**
     * Formats the user's message to be able to differentiate between special commands
     *
     * @param message The user's message to be formatted
     * @return Return a String array which contains the split message. The first element is used to differentiate
     * whether it's a message, command, or command which require special formatting
     */
    public String[] formatCommands(String message) {
        return message.split(" ");
    }

    /**
     * Converts string consisting of a date and time to LocalDate object
     *
     * @param dateTime The date and time which the task should be completed by
     * @return Returns a LocalDate object
     * @throws FunBoxExceptions If the length of dateTimeArr != 2
     */
    public LocalDate stringToLocalDate(String dateTime) throws FunBoxExceptions {
        String[] dateTimeArr = this.formatCommands(dateTime);
        LocalDate result;

        if (dateTimeArr.length < 2) {
            throw new FunBoxExceptions("ERROR! Please ensure date and time"
                    + " follows the following format: <date> <time>");
        }

        result = LocalDate.parse(dateTimeArr[0], DateTimeFormatter.ofPattern(dateFormat));

        return result;
    }


    /**
     * Gets the time from a string consisting of a date and time
     *
     * @param dateTime The date and time which the task should be completed by
     * @return Returns a time
     * @throws FunBoxExceptions If the length of dateTimeArr != 2
     */
    public String getTime(String dateTime) throws FunBoxExceptions {

        String[] dateTimeArr = formatCommands(dateTime);

        if (dateTimeArr.length != 2) {
            throw new FunBoxExceptions("ERROR! Please ensure date and time"
                    + " is in this format: yyyy-mm-dd time");
        }

        return dateTimeArr[1];
    }
}
