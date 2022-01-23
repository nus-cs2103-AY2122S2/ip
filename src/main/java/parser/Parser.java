package parser;

import commands.*;
import task.*;

import exception.InvalidInputException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;

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
        if (task.trim().length() == 0)
            throw new InvalidInputException("Task description can not be empty!");
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
            throw new InvalidInputException("Invalid format. " +
                    "eg. deadline return book /by 02/12/2019 18:00.");
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
        int cmmand_len = arr.length;
        switch (arr[0]) {
            case ExitCommand.COMMAND_WORD:
                if (cmmand_len == 1) {
                    return new ExitCommand();
                }
                break;
            case ListCommand.COMMAND_WORD:
                if (cmmand_len == 1) {
                    // list without date
                    return new ListCommand();
                } else if (arr.length == 2) {
                    // list with date given
                    return new ListCommand(true, parseDate(arr[1]));
                }
                break;
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(parseListIndex(arr[1], tasks));
                // Fallthrough
            case UnMarkCommand.COMMAND_WORD:
                return new UnMarkCommand(parseListIndex(arr[1], tasks));
                // Fallthrough
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(parseListIndex(arr[1], tasks));
                // Fallthrough
            case AddCommand.COMMAND_TODO:
                return new AddCommand(new ToDo(checkEmptyTask(arr[1])));
                // Fallthrough
            case AddCommand.COMMAND_DEADLINE:
                String[] deadline = parseTaskFormat(arr[1]);
                String deadline_date = checkPrepositionFormat(deadline[1], AddCommand.COMMAND_DEADLINE);
                return new AddCommand(new Deadline(checkEmptyTask(deadline[0]), parseDate(deadline_date)));
                // Fallthrough
            case AddCommand.COMMAND_EVENT:
                String[] event = parseTaskFormat(arr[1]);
                String event_date = checkPrepositionFormat(event[1], AddCommand.COMMAND_EVENT);
                return new AddCommand(new Event(checkEmptyTask(event[0]), parseDate(event_date)));
                // Fallthrough
            default:
                throw new InvalidInputException("Invalid command. Please try 'list/bye/mark/unmark/event/deadline/todo'. ");
        }
        throw new InvalidInputException("Invalid command. Please try 'list/bye/mark/unmark/event/deadline/todo'. ");
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
            throw new InvalidInputException("'" + date + "' can't be formatted! Please format the date/time as dd/MM/yyyy HH:mm");
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
