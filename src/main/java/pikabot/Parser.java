package pikabot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import pikabot.command.Command;
import pikabot.command.DeadlineCommand;
import pikabot.command.DeleteCommand;
import pikabot.command.EventCommand;
import pikabot.command.FindCommand;
import pikabot.command.InvalidCommand;
import pikabot.command.ListCommand;
import pikabot.command.MarkCommand;
import pikabot.command.TodoCommand;
import pikabot.command.UnmarkCommand;
import pikabot.exception.DeadlineException;
import pikabot.exception.EventException;
import pikabot.exception.FindException;
import pikabot.exception.NoIntegerException;
import pikabot.exception.TodoException;
import pikabot.task.Deadline;
import pikabot.task.Event;
import pikabot.task.Todo;


/**
 * Parses user input from CLI into a Task command.
 */
public class Parser {

    /**
     * Returns a command after parsing input from user.
     *
     * @param inputArr Array containing input string from user.
     * @return Command.
     */
    public static Command parseCommand(String[] inputArr) {

        inputArr[0] = inputArr[0].toLowerCase(Locale.ROOT);
        switch(inputArr[0]) {
        case "deadline":
        case "d": {
            return new DeadlineCommand(inputArr);
        }
        case "delete":
        case "del": {
            return new DeleteCommand(inputArr);
        }
        case "event":
        case "e": {
            return new EventCommand(inputArr);
        }
        case "list": {
            return new ListCommand(inputArr);
        }
        case "mark":
        case "m": {
            return new MarkCommand(inputArr);
        }
        case "todo":
        case "t": {
            return new TodoCommand(inputArr);
        }
        case "unmark":
        case "um": {
            return new UnmarkCommand(inputArr);
        }
        case "find": {
            return new FindCommand(inputArr);
        }
        default: {
            return new InvalidCommand(inputArr);
        }
        }
    }

    /**
     * Returns a Todo command after parsing input from user.
     *
     * @param todoArray String array containing input string from user.
     * @return Todo command.
     * @throws TodoException If input is invalid.
     */
    public static Todo parseTodo(String[] todoArray) throws TodoException {
        if (todoArray.length == 1) {
            throw new TodoException();
        } else {
            String description = todoArray[1];
            return new Todo(description);
        }
    }

    /**
     * Returns a Deadline command after parsing input from user.
     *
     * @param deadlineArray String array containing input string from user.
     * @return Deadline command.
     * @throws DeadlineException If input is invalid.
     */
    public static Deadline parseDeadline(String[] deadlineArray) throws DeadlineException {
        if (deadlineArray.length == 1) {
            throw new DeadlineException("Description of deadline cannot be empty! "
                + "Please enter a deadline in this format:"
                + "\"deadline description /by YYYY-MM-DD\"");
        } else {
            String[] deadlineDetails = deadlineArray[1].split("/by ", 2);
            if (deadlineDetails.length == 1) {
                throw new DeadlineException("Please enter a deadline in this format: "
                    + "\"deadline description /by YYYY-MM-DD\"");
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    return new Deadline(deadlineDetails[0], LocalDate.parse(deadlineDetails[1], dtf));
                } catch (DateTimeParseException e) {
                    throw new DeadlineException("Invalid deadline! Deadline has to be a "
                            + "valid date in numerical format YYYY-MM-DD");
                }
            }
        }
    }

    /**
     * Returns an Event command after parsing input from user.
     *
     * @param eventArray String array containing input string from user.
     * @return Event command.
     * @throws EventException If input is invalid.
     */
    public static Event parseEvent(String[] eventArray) throws EventException {
        if (eventArray.length == 1) {
            throw new EventException("The description of an event cannot be empty! "
                + "Please enter an event in this format: "
                + "\"event description /at YYYY-MM-DD\"");
        } else {
            String[] eventDetails = eventArray[1].split("/at ", 2);
            if (eventDetails.length == 1) {
                throw new EventException("Please enter an event in this format: "
                    + "\"event description /at YYYY-MM-DD\"");
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    return new Event(eventDetails[0], LocalDate.parse(eventDetails[1], dtf));
                } catch (DateTimeParseException e) {
                    throw new EventException("Invalid event! Event has to have a valid date in "
                            + "numerical format YYYY-MM-DD");
                }
            }
        }
    }

    /**
     * Checks whether command to search for tasks containing a keyword is valid.
     *
     * @param findArray String array containing input string from user.
     * @throws FindException If no keyword is entered.
     */
    public static void parseFindCommand(String[] findArray) throws FindException {
        if (findArray.length == 1) {
            throw new FindException();
        }
    }

    /**
     * Checks whether command contains an integer input.
     *
     * @param markArray String array containing input string from user.
     * @throws NoIntegerException If no integer is given.
     */
    public static void parseIntegerCommand(String[] markArray) throws NoIntegerException {
        if (markArray.length == 1) {
            throw new NoIntegerException("Please enter a task number!");
        }
        try {
            Integer.parseInt(markArray[1]);
        } catch (NumberFormatException e) {
            throw new NoIntegerException("Please enter a valid task number!");
        }
    }

}
