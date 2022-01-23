package parser;

import commands.*;
import exception.InvalidInputException;
import task.*;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Parser {
    public String checkTaskFormat(String task) throws InvalidInputException {
        if (task.trim().length() == 0)
            throw new InvalidInputException("Task description can not be empty!");
        return task.trim();

    }

    public int parseListIndex(String index, List<Task> tasks) throws InvalidInputException {
        int num = 0;
        try {
            num = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid task number. Please enter a valid integer.");
        }
        if (num < 0 || num > tasks.size())
            throw new InvalidInputException("Task number out of range.");
        return num;
    }

    public String[] parseTaskFormat(String str) throws InvalidInputException {
        String[] output = str.split("/", 2);

        if (output.length == 1)
            throw new InvalidInputException("Invalid format. eg. deadline return book /by 02/12/2019 18:00.");

        return output;
    }

    public String checkPrepositionFormat(String str, String command) throws InvalidInputException {
        String[] date = str.split(" ", 2);

        if (date.length != 2)
            throw new InvalidInputException("Invalid format. eg. deadline return book /by 02/12/2019 18:00.");

        String pre = date[0];
        // check Prepositions of Time/Date
        if (command.equals(AddCommand.COMMAND_DEADLINE) && !pre.equals("by"))
            throw new InvalidInputException("Invalid Prepositions for deadline command. eg. '/by'.");
        if (command.equals(AddCommand.COMMAND_EVENT) && !pre.equals("on") && !pre.equals("at"))
            throw new InvalidInputException("Invalid Prepositions for event command. eg. '/on' or '/at' .");

        return date[1];
    }


    public Command parseUserInput(String userInput, List<Task> tasks) throws InvalidInputException {
        // catch empty input
        if (userInput.trim().length() == 0)
            throw new InvalidInputException("Empty Command");

        String[] arr = userInput.split(" ", 2);
        int cmmand_len = arr.length;
        switch (arr[0]) {
            case ExitCommand.COMMAND_WORD:
                if (cmmand_len == 1)
                    return new ExitCommand();
                break;
            case ListCommand.COMMAND_WORD:
                if (cmmand_len == 1)
                    // list without date
                    return new ListCommand();
                else if (arr.length == 2)
                    // list with date given
                    return new ListCommand(true, parseDate(arr[1]));
                break;
            case MarkCommand.COMMAND_WORD:
                return new MarkCommand(parseListIndex(arr[1], tasks));
            case UnMarkCommand.COMMAND_WORD:
                return new UnMarkCommand(parseListIndex(arr[1], tasks));
            case DeleteCommand.COMMAND_WORD:
                return new DeleteCommand(parseListIndex(arr[1], tasks));
            case AddCommand.COMMAND_TODO:
                return new AddCommand(new ToDo(checkTaskFormat(arr[1])));
            case AddCommand.COMMAND_DEADLINE:
                String[] deadline = parseTaskFormat(arr[1]);
                String deadline_date = checkPrepositionFormat(deadline[1], AddCommand.COMMAND_DEADLINE);
                return new AddCommand(new Deadline(checkTaskFormat(deadline[0]), parseDate(deadline_date)));
            case AddCommand.COMMAND_EVENT:
                String[] event = parseTaskFormat(arr[1]);
                String event_date = checkPrepositionFormat(event[1], AddCommand.COMMAND_EVENT);
                return new AddCommand(new Event(checkTaskFormat(event[0]), parseDate(event_date)));
            default:
                throw new InvalidInputException("Invalid command. Please try 'list/bye/mark/unmark/event/deadline/todo'. ");
        }
        throw new InvalidInputException("Invalid command. Please try 'list/bye/mark/unmark/event/deadline/todo'. ");
    }

    public static LocalDateTime parseDate(String date) throws InvalidInputException {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime formattedDate = LocalDateTime.parse(date, format);
            return formattedDate;
        } catch (DateTimeException e) {
            throw new InvalidInputException("'" + date + "' can't be formatted! Please format the date/time as dd/MM/yyyy HH:mm");
        }
    }

    public static String printDate(LocalDateTime date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
        return date.format(format);
    }

}
