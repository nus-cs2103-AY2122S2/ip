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
import meep.commands.UnmarkCommand;
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
        boolean isEmptyInput = task.trim().length() == 0;
        if (isEmptyInput) {
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
        int num;
        try {
            num = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid task number. Please enter a valid integer.");
        }
        boolean isOutOfBound = num < 0 || num > tasks.size();
        if (isOutOfBound) {
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
        boolean isValidFormat = output.length == 1;
        if (isValidFormat) {
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
        boolean isValidFormat = date.length != 2;
        if (isValidFormat) {
            throw new InvalidInputException("Invalid format. eg. deadline return book /by 02/12/2019 18:00.");
        }

        String pre = date[0];
        // check Prepositions of Time/Date
        boolean isDeadlineCommand = command.equals(AddCommand.COMMAND_DEADLINE);
        boolean isValidDeadlinePrepositions = pre.equals("by");
        if (isDeadlineCommand && !isValidDeadlinePrepositions) {
            throw new InvalidInputException("Invalid Prepositions for deadline command. eg. '/by'.");
        }

        boolean isEventCommand = command.equals(AddCommand.COMMAND_EVENT);
        boolean isValidEventPrepositions = pre.equals("on") || pre.equals("at");
        if (isEventCommand && !isValidEventPrepositions) {
            throw new InvalidInputException("Invalid Prepositions for event command. eg. '/on' or '/at' .");
        }

        return date[1];
    }

    /**
     * Checks command length.
     *
     * @param length the correct length of the command.
     * @param input the input array.
     * @return is valid command length or not.
     */
    private boolean isValidCommandLength(int length, String[] input) {
        return length == input.length;
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
        boolean isEmptyInput = userInput.trim().length() == 0;
        if (isEmptyInput) {
            throw new InvalidInputException("Empty Command");
        }

        String[] arr = userInput.split(" ", 2);
        switch (arr[0]) {
        case ExitCommand.COMMAND_WORD:
            if (isValidCommandLength(ExitCommand.COMMAND_LENGTH, arr)) {
                return new ExitCommand();
            }
            break;
        case ListCommand.COMMAND_WORD:
            if (isValidCommandLength(ListCommand.COMMAND_LIST_LENGTH, arr)) {
                // list without date
                return new ListCommand();
            } else if (isValidCommandLength(ListCommand.COMMAND_LIST_DATE_LENGTH, arr)) {
                // list with date given
                return new ListCommand(true, parseDate(arr[1]));
            }
            break;
        case MarkCommand.COMMAND_WORD:
            if (isValidCommandLength(MarkCommand.COMMAND_LENGTH, arr)) {
                int index = parseListIndex(arr[1], tasks);
                assert index >= 0 && index < tasks.size() : "index should >= 0 and less than task list size";
                return new MarkCommand(index);
            }
            break;
        case UnmarkCommand.COMMAND_WORD:
            if (isValidCommandLength(UnmarkCommand.COMMAND_LENGTH, arr)) {
                int index = parseListIndex(arr[1], tasks);
                assert index >= 0 && index < tasks.size() : "index should >= 0 and less than task list size";
                return new UnmarkCommand(index);
            }
            break;
        case DeleteCommand.COMMAND_WORD:
            if (isValidCommandLength(DeleteCommand.COMMAND_LENGTH, arr)) {
                int index = parseListIndex(arr[1], tasks);
                assert index >= 0 && index < tasks.size() : "index should >= 0 and less than task list size";
                return new DeleteCommand(index);
            }
            break;
        case AddCommand.COMMAND_TODO:
            if (isValidCommandLength(AddCommand.COMMAND_LENGTH, arr)) {
                String taskTitle = checkEmptyTask(arr[1]);
                assert taskTitle.length() > 0 : "task title should not be empty";
                return new AddCommand(new ToDo(taskTitle));
            }
            break;
        case AddCommand.COMMAND_DEADLINE:
            String[] deadline = parseTaskFormat(arr[1]);
            assert deadline.length == 2 : "arr length should be 2";
            String deadlineDate = checkPrepositionFormat(deadline[1], AddCommand.COMMAND_DEADLINE);
            String deadlineTitle = checkEmptyTask(deadline[0]);
            assert deadlineTitle.length() > 0 : "task title should not be empty";
            return new AddCommand(new Deadline(deadlineTitle, parseDate(deadlineDate)));
            // Fallthrough
        case AddCommand.COMMAND_EVENT:
            String[] event = parseTaskFormat(arr[1]);
            assert event.length == 2 : "arr length should be 2";
            String eventDate = checkPrepositionFormat(event[1], AddCommand.COMMAND_EVENT);
            String eventTitle = checkEmptyTask(event[0]);
            assert eventTitle.length() > 0 : "task title should not be empty";
            return new AddCommand(new Event(eventTitle, parseDate(eventDate)));
            // Fallthrough
        case FindCommand.COMMAND_WORD:
            if (isValidCommandLength(FindCommand.COMMAND_LENGTH, arr)) {
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
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
        return date.format(format);
    }

}
