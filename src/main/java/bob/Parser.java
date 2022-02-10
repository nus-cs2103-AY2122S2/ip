package bob;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import bob.command.ByeCommand;
import bob.command.Command;
import bob.command.DeadlineCommand;
import bob.command.DeleteCommand;
import bob.command.EventCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.command.PriorityCommand;
import bob.command.PriorityListCommand;
import bob.command.ToDoCommand;
import bob.exception.BobException;
import bob.exception.DeadlineException;
import bob.exception.EventException;
import bob.exception.FindException;
import bob.exception.InvalidCommandException;
import bob.exception.InvalidIndexException;
import bob.exception.PriorityException;
import bob.exception.ToDoException;


/**
 * Represents the parser for the Bob program.
 * Makes sense of the user input and translates it to a command.
 */
public class Parser {

    /**
     * Returns a Command that represents the user's inputs.
     * @param input a string representing the user's input
     * @return Command that was interpreted from user input
     * @throws BobException exceptions that tells Bob to let the user know what went wrong
     */
    public static Command parse(String input) throws BobException {
        String firstWord = input.split(" ")[0];
        if (firstWord.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (firstWord.equalsIgnoreCase("mark")) {
            return parseMark(input);
        } else if (firstWord.equalsIgnoreCase("delete")) {
            return parseDelete(input);
        } else if (firstWord.equalsIgnoreCase("deadline")) {
            return parseDeadline(input);
        } else if (firstWord.equalsIgnoreCase("todo")) {
            return parseToDo(input);
        } else if (firstWord.equalsIgnoreCase("event")) {
            return parseEvent(input);
        } else if (firstWord.equalsIgnoreCase("bye")) {
            return new ByeCommand();
        } else if (firstWord.equalsIgnoreCase("find")) {
            return parseFind(input);
        } else if (firstWord.equalsIgnoreCase("priority")) {
            return parsePriority(input);
        } else if (firstWord.equalsIgnoreCase("prioritylist")) {
            return new PriorityListCommand();
        } else {
            throw new InvalidCommandException();
        }
    }

    private static PriorityCommand parsePriority(String input) {
        String priorityString = input.substring(8).trim();
        String[] inputArr = priorityString.split(" ");
        if (priorityString.isBlank() || inputArr.length != 2) {
            throw new PriorityException();
        }
        try {
            int index = Integer.parseInt(inputArr[0]) - 1;
            String priority = inputArr[1];
            return new PriorityCommand(index, priority);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    private static MarkCommand parseMark(String input) {
        String indexString = input.substring(4).trim();
        if (indexString.isBlank()) {
            throw new InvalidIndexException();
        }
        try {
            int index = Integer.parseInt(indexString) - 1;
            return new MarkCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    private static DeleteCommand parseDelete(String input) {
        String indexString = input.substring(6).trim();
        if (indexString.isBlank()) {
            throw new InvalidIndexException();
        }
        try {
            int index = Integer.parseInt(indexString) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException();
        }
    }

    private static DeadlineCommand parseDeadline(String input) {
        String[] strArr = input.substring(8).split("/by");
        if (strArr.length <= 1) {
            throw new DeadlineException();
        }
        String taskName = strArr[0].trim();
        try {
            LocalDateTime dateTime = LocalDateTime.parse(strArr[1].trim());
            return new DeadlineCommand(taskName, dateTime);
        } catch (DateTimeParseException e) {
            throw new DeadlineException();
        }
    }

    private static ToDoCommand parseToDo(String input) {
        String taskName = input.substring(4).trim();
        String[] strArr = input.substring(4).split(" ");
        if (strArr.length <= 0 || (strArr.length == 1 && strArr[0].isBlank())) {
            throw new ToDoException();
        }
        return new ToDoCommand(taskName);
    }

    private static EventCommand parseEvent(String input) {
        String[] strArr = input.substring(5).split("/at");
        if (strArr.length <= 1) {
            throw new EventException();
        }
        try {
            String taskName = strArr[0].trim();
            String[] dateTime = strArr[1].trim().split("T");
            LocalDate date = LocalDate.parse(dateTime[0]);
            String[] times = dateTime[1].split("-");
            LocalTime startTime = LocalTime.parse(times[0]);
            LocalTime endTime = LocalTime.parse(times[1]);
            return new EventCommand(taskName, date, startTime, endTime);
        } catch (DateTimeParseException e) {
            throw new EventException();
        }
    }

    private static FindCommand parseFind(String input) {
        String searchInput = input.substring(4).trim();
        if (searchInput.isBlank()) {
            throw new FindException();
        }
        return new FindCommand(searchInput);
    }
}
