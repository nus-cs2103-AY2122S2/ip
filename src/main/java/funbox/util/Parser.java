package funbox.util;

import java.time.LocalDate;
import funbox.command.Command;
import funbox.command.ByeCommand;
import funbox.command.ListCommand;
import funbox.command.ToDoCommand;
import funbox.command.EventCommand;
import funbox.command.DeadlineCommand;
import funbox.command.DeleteCommand;
import funbox.command.FilterCommand;
import funbox.command.MarkCommand;
import funbox.command.UnmarkCommand;
import funbox.command.FindCommand;
import funbox.exception.FunBoxExceptions;


public class Parser {

    public final String unknownCommand = "Sorry! You have used the command wrongly!";

    /**
     * Parse user input into commands.
     *
     * @param command The user's command to be parsed into a Command object.
     * @param taskList The tasklist containing the user's tasks.
     * @param ui Use for outputting messages for interaction with the users.
     * @return Return a command object.
     * @throws FunBoxExceptions If commandType != any of the strings in the switch-case.
     */
    public Command parseCommand(String command, TaskList taskList, Ui ui) throws FunBoxExceptions {
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
            return new DeleteCommand(Integer.parseInt(description));
        case "mark":
            return new MarkCommand(Integer.parseInt(description));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(description));
        case "filter":
            return new FilterCommand(description);
        case "find":
            return new FindCommand(description);
        default:
            throw new FunBoxExceptions("ERROR! I do not know what the commands means :<");
        }
    }

    /**
     * Concate the string in an array into a single string.
     *
     * @param parsedCommand The array containing the string to be concatenated.
     * @param startingIndex The index to start concatenating from.
     * @return Return a concatenated string.
     */
    public String concatFormat(String[] parsedCommand, int startingIndex) {
        String result = "";
        for (int i = startingIndex; i < parsedCommand.length; i++) {
            if (i == parsedCommand.length - 1) {
                result = result.concat(parsedCommand[i]);
            } else {
                result = result.concat(parsedCommand[i]).concat(" ");
            }
        }

        return result;
    }

    /**
     * Get the description and a date from a string.
     *
     * @param message The message to be parsed to get the description and date.
     * @param type The type of task the message is.
     * @return Return a string array where the first index is the description, and the second is the date.
     */
    public String[] getDescriptionAndDate(String message, String type) {
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
        // Split message by blank space
        return message.split(" ");
    }

    /**
     * Converts string consisting of a date and time to LocalDate object
     *
     * @param dateTime The date and time which the task should be completed by
     * @return Returns a LocalDate object
     * @throws FunBoxExceptions If dateTimeArr.length != 2
     */
    public LocalDate stringToLocalDate(String dateTime) throws FunBoxExceptions {
        String[] dateTimeArr = this.formatCommands(dateTime);
        LocalDate result;
        if (dateTimeArr.length == 2) {
            result = LocalDate.parse(dateTimeArr[0]);
        } else {
            throw new FunBoxExceptions("ERROR! Please ensure date and time"
                    + " is in the correct format: yyyy-mm-dd time");
        }

        return result;
    }


    /**
     * Gets the time from a string consisting of a date and time
     *
     * @param dateTime The date and time which the task should be completed by
     * @return Returns a time
     * @throws FunBoxExceptions If dateTimeArr.length != 2
     */
    public String getTime(String dateTime) throws FunBoxExceptions {
        String[] dateTimeArr = this.formatCommands(dateTime);
        String time;
        if (dateTimeArr.length == 2) {
            time = dateTimeArr[1];
        } else {
            throw new FunBoxExceptions("ERROR! Please ensure date and time"
                    + " is in this format: yyyy-mm-dd time");
        }
        return time;
    }
}
