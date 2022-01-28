package meep.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import meep.commands.AddCommand;
import meep.commands.Command;
import meep.commands.DeleteCommand;
import meep.commands.ExitCommand;
import meep.commands.FindCommand;
import meep.commands.ListCommand;
import meep.commands.MarkCommand;
import meep.commands.UnMarkCommand;
import meep.exception.InvalidInputException;
import meep.task.Deadline;
import meep.task.Event;
import meep.task.Task;
import meep.task.ToDo;


/**
 * Parses user input.
 */
public class Parser {
    /**
     * Checks task title is empty or not.
     *
     * @param task the task.
     * @return the task title after removing leading and trailing spaces.
     * @throws InvalidInputException If the input can't be split into two part.
     */
    public String checkEmptyTask(String task) throws InvalidInputException {
        if (task.trim().length() == 0) {
            throw new InvalidInputException("Task description can not be empty!");
        }
        return task.trim();

    }

    /**
     * Parses list index.
     *
     * @param index the index.
     * @param tasks the tasks list.
     * @return the formatted index int.
     * @throws InvalidInputException If the index is out of bound.
     */
    public int parseListIndex(String index, List<Task> tasks) throws InvalidInputException {
        int num = 0;
        try {
            num = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid task number. Please enter a valid integer.");
        }
        if (num < 0 || num > tasks.size()) {
            throw new InvalidInputException("Task number out of range.");
        }
        return num;
    }

    /**
     * Separates the inputs by '/' into two part.
     *
     * @param str the str to parse.
     * @return the parsed string.
     * @throws InvalidInputException If the input can't be split into two part.
     */
    public String[] parseTaskFormat(String str) throws InvalidInputException {
        String[] output = str.split("/", 2);

        if (output.length == 1) {
            throw new InvalidInputException("Invalid format. "
                    + "eg. deadline return book /by 02/12/2019 18:00.");
        }

        return output;
    }

    /**
     * Checks preposition before the datetime.
     *
     * @param str     the datetime string.
     * @param command the command.
     * @return the parsed datetime.
     * @throws InvalidInputException If the prepositions is invalid.
     */
    public String checkPrepositionFormat(String str, String command) throws InvalidInputException {
        String[] date = str.split(" ", 2);

        if (date.length != 2) {
            throw new InvalidInputException("Invalid format. eg. deadline return book /by 02/12/2019 18:00.");
        }

        String pre = date[0];
        // check Prepositions of Time/Date
        if (command.equals(AddCommand.COMMAND_DEADLINE) && !pre.equals("by")) {
            throw new InvalidInputException("Invalid Prepositions for deadline command. eg. '/by'.");
        }
        if (command.equals(AddCommand.COMMAND_EVENT) && !pre.equals("on") && !pre.equals("at")) {
            throw new InvalidInputException("Invalid Prepositions for event command. eg. '/on' or '/at' .");
        }

        return date[1];
    }


    /**
     * Parses user input command.
     *
     * @param userInput the user input.
     * @param tasks     the tasks lsit.
     * @return the command.
     * @throws InvalidInputException If the input is invalid.
     */
    public Command parseUserInput(String userInput, List<Task> tasks) throws InvalidInputException {
        // catch empty input
        if (userInput.trim().length() == 0) {
            throw new InvalidInputException("Empty Command");
        }

        String[] arr = userInput.split(" ", 2);
        int commandLen = arr.length;
        switch (arr[0]) {
        case ExitCommand.COMMAND_WORD:
            if (commandLen == 1) {
                return new ExitCommand();
            }
            break;
        case ListCommand.COMMAND_WORD:
            if (commandLen == 1) {
                // list without date
                return new ListCommand();
            } else if (commandLen == 2) {
                // list with date given
                return new ListCommand(true, parseDate(arr[1]));
            }
            break;
        case MarkCommand.COMMAND_WORD:
            if (commandLen == 2) {
                return new MarkCommand(parseListIndex(arr[1], tasks));
            }
            break;
        case UnMarkCommand.COMMAND_WORD:
            if (commandLen == 2) {
                return new UnMarkCommand(parseListIndex(arr[1], tasks));
            }
            break;
        case DeleteCommand.COMMAND_WORD:
            if (commandLen == 2) {
                return new DeleteCommand(parseListIndex(arr[1], tasks));
            }
            break;
        case AddCommand.COMMAND_TODO:
            return new AddCommand(new ToDo(checkEmptyTask(arr[1])));
            // Fallthrough
        case AddCommand.COMMAND_DEADLINE:
            String[] deadline = parseTaskFormat(arr[1]);
            String deadlineDate = checkPrepositionFormat(deadline[1], AddCommand.COMMAND_DEADLINE);
            return new AddCommand(new Deadline(checkEmptyTask(deadline[0]), parseDate(deadlineDate)));
            // Fallthrough
        case AddCommand.COMMAND_EVENT:
            String[] event = parseTaskFormat(arr[1]);
            String eventDate = checkPrepositionFormat(event[1], AddCommand.COMMAND_EVENT);
            return new AddCommand(new Event(checkEmptyTask(event[0]), parseDate(eventDate)));
            // Fallthrough
        case FindCommand.COMMAND_WORD:
            if (commandLen == 2) {
                return new FindCommand(arr[1]);
            }
            break;

        default:
            throw new InvalidInputException("Invalid command. "
                    + "Please try 'list/bye/mark/unmark/event/deadline/todo/find'. ");
        }
        throw new InvalidInputException("Invalid command. "
                + "Please try 'list/bye/mark/unmark/event/deadline/todo/find'. ");
    }

    /**
     * Parses string to datetime.
     *
     * @param date the date
     * @return the local date time
     * @throws InvalidInputException If the datetime format is invalid.
     */
    public static LocalDateTime parseDate(String date) throws InvalidInputException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime formattedDate = LocalDateTime.parse(date, format);
            return formattedDate;
        } catch (DateTimeException e) {
            throw new InvalidInputException("'" + date + "' can't be formatted! "
                    + "Please format the date/time as dd/MM/yyyy HH:mm");
        }
    }

    /**
     * Prints date in string.
     *
     * @param date the date.
     * @return the datetime string.
     */
    public static String printDate(LocalDateTime date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return date.format(format);
    }

}
