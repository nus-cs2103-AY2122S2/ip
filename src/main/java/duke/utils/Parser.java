package duke.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.ClearAllCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ShowAllTasksOnSameDateCommand;
import duke.command.UnmarkCommand;
import duke.command.ViewSchedulesCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * The type Parser.
 */
public class Parser {
    /**
     * Instantiates a new Parser.
     */
    public Parser() {}

    /**
     * Parse input to Command.
     *
     * @param input the user input
     * @return the specific Cortana command
     * @throws CortanaException the cortana exception
     */
    public static Command parse(String input) throws CortanaException {
        boolean isExit = input.toLowerCase().replaceAll("[ |\\t]", "").equals("bye");
        boolean isListCommand = input.toLowerCase().replaceAll("[ |\\t]", "").equals("list");
        boolean isMarkCommand = input.toLowerCase().matches("^mark(\\s.*)?");
        boolean isUnmarkCommand = input.toLowerCase().matches("^unmark(\\s.*)?");
        boolean isDeleteCommand = input.toLowerCase().matches("^delete(\\s.*)?");
        boolean isClearAllCommand = input.toLowerCase().matches("^clear all\\s*");
        boolean isShowAllOnSameDateCommand = input.toLowerCase()
                .matches("^show all(\\s.*)?");
        boolean isViewSchedulesCommand = input.toLowerCase()
                .matches("^view schedules(\\s.*)?");
        boolean isFindCommand = input.toLowerCase().matches("^find( )? .*");
        boolean isAddCommand = input.toLowerCase().matches("(^todo(\\s.*)?)?(^deadline(\\s.*)?)?(^event(\\s.*)?)?");
        if (isExit) {
            return parseToExitCommand();
        } else if (isListCommand) {
            return parseToListCommand();
        } else if (isMarkCommand || isUnmarkCommand || isDeleteCommand) {
            return parseToCommandWithIndexes(input, isMarkCommand, isUnmarkCommand, isDeleteCommand);
        } else if (isClearAllCommand) {
            return parseToClearAllCommand();
        } else if (isShowAllOnSameDateCommand || isViewSchedulesCommand) {
            return parseToCommandWithDate(input, isShowAllOnSameDateCommand, isViewSchedulesCommand);
        } else if (isFindCommand) {
            return parseToFindCommand(input);
        } else if (isAddCommand) {
            return parseToAddCommand(input);
        } else {
            throw new CortanaException("I don't know what that means :(");
        }
    }

    /**
     * Parse the user input date/time to LocalDateTime
     *
     * @param dateTime the input date/time
     * @return the actual date/time in LocalDateTime
     * @throws CortanaException the cortana exception
     */
    public static LocalDateTime parseLocalDateTime(String dateTime) throws CortanaException {
        boolean correctTimeFormat = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}( \\d{4})?")
                .matcher(dateTime).find();
        boolean hasTime = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2} \\d{4}").matcher(dateTime).find();
        LocalDateTime localDateTime;

        if (!correctTimeFormat) {
            throw new CortanaException("Invalid date time format!" + " Please follow the format yyyy-M-d HHmm!");
        }
        try {
            if (hasTime) {
                localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-M-d HHmm"));
            } else {
                LocalDate localDate = LocalDate.parse(dateTime,
                        DateTimeFormatter.ofPattern("yyyy-M-d"));
                localDateTime = LocalDateTime.of(localDate, LocalTime.MAX);
            }
            return localDateTime;
        } catch (DateTimeParseException e) {
            /* unable to parse user input date/time */
            throw new CortanaException("Invalid date/time!");
        }
    }

    /**
     * Parse to exit command.
     *
     * @return the exit command
     */
    public static Command parseToExitCommand() {
        return new ExitCommand();
    }

    /**
     * Parse to list command.
     *
     * @return the list command
     */
    public static Command parseToListCommand() {
        return new ListCommand();
    }

    /**
     * Parse to clear all command.
     *
     * @return the clear all command
     */
    public static Command parseToClearAllCommand() {
        return new ClearAllCommand();
    }

    /**
     * Parse to command with indexes.
     * Namely, the mark command, unmark command and delete command.
     *
     * @param input           the input
     * @param isMarkCommand   whether the input is a mark command
     * @param isUnmarkCommand whether the input is an unmark command
     * @param isDeleteCommand whether the input is a delete command
     * @return one of the three commands: mark command, unmark command or delete command
     * @throws CortanaException the cortana exception
     */
    public static Command parseToCommandWithIndexes(
            String input, boolean isMarkCommand, boolean isUnmarkCommand, boolean isDeleteCommand)
            throws CortanaException {
        assert isMarkCommand != isDeleteCommand && isUnmarkCommand == isDeleteCommand
                || isUnmarkCommand != isMarkCommand && isMarkCommand == isDeleteCommand
                || isDeleteCommand != isMarkCommand && isMarkCommand == isUnmarkCommand;

        String[] indexesString = input.replaceAll(
                isMarkCommand ? "mark +" : isUnmarkCommand ? "unmark +" : "delete +", "").split(" ");
        try {
            int[] arr = Arrays.stream(indexesString).mapToInt(i -> Integer.parseInt(i) - 1).toArray();
            if (isMarkCommand) {
                return new MarkCommand(arr);
            }
            if (isUnmarkCommand) {
                return new UnmarkCommand(arr);
            }
            return new DeleteCommand(arr);
        } catch (NumberFormatException e) {
            throw new CortanaException("Please specify the task number(s)!");
        }
    }

    /**
     * Parse to command with date.
     *
     * @param input                      the input
     * @param isShowAllOnSameDateCommand whether the input is a show all tasks on the same date/time command
     * @param isViewSchedulesCommand     whether the input is a view schedules on a date command
     * @return one of the two commands: show all tasks on the same date/time command or view schedules on a date command
     * @throws CortanaException the cortana exception
     */
    public static Command parseToCommandWithDate(
            String input, boolean isShowAllOnSameDateCommand, boolean isViewSchedulesCommand) throws CortanaException {
        assert isShowAllOnSameDateCommand == !isViewSchedulesCommand;

        boolean isWithoutDateTime = input.replaceAll(" ", "")
                .matches(isShowAllOnSameDateCommand ? "showall" : "viewschedules");
        if (isWithoutDateTime) {
            /* user did not specify time */
            throw new CortanaException("Please specify the date time you are looking for!");
        }

        String dateTimeString = input.replaceAll(isShowAllOnSameDateCommand ? "show all\\s+"
                : "view schedules\\s+", "");

        if (isShowAllOnSameDateCommand) {
            return new ShowAllTasksOnSameDateCommand(parseLocalDateTime(dateTimeString), dateTimeString);
        }
        /* user input a time for view schedules */
        boolean isWithTime = dateTimeString.split(" ").length == 2;
        if (isWithTime) {
            throw new CortanaException("Please only input the date to view schedules!");
        }
        return new ViewSchedulesCommand(parseLocalDateTime(dateTimeString).toLocalDate(), dateTimeString);
    }

    /**
     * Parse to find command.
     *
     * @param input the input
     * @return the find command
     * @throws CortanaException the cortana exception
     */
    public static FindCommand parseToFindCommand(String input) throws CortanaException {
        String keyWord = input.replaceAll("find ", "");
        if (keyWord.isEmpty()) {
            throw new CortanaException("Please specify the keyword you are looking for!");
        } else {
            return new FindCommand(keyWord);
        }
    }

    /**
     * Parse to add command.
     *
     * @param input the input
     * @return the add command with one of the three task types: deadline, event or todo
     * @throws CortanaException the cortana exception
     */
    public static AddCommand parseToAddCommand(String input) throws CortanaException {
        boolean hasDescription = input.toLowerCase()
                .matches("(^todo\\s.+\\w.*)?(^deadline\\s.+\\w.*)?(^event\\s.+\\w.*)?");
        boolean isTodo = input.toLowerCase().matches("^todo(\\s.*)?");
        boolean isDeadline = input.toLowerCase().matches("^deadline(\\s.*)?");
        boolean isEvent = input.toLowerCase().matches("^event(\\s.*)?");
        if (!hasDescription) {
            String aOrAn = isEvent ? "an " : "a ";
            TaskType taskType = isDeadline ? TaskType.DEADLINE : isEvent ? TaskType.EVENT : TaskType.TODO;
            throw new CortanaException("OOPS!!! The description of " + aOrAn + taskType
                    + " cannot be empty!");
        }
        boolean hasTimeKeyword = Pattern.compile(isDeadline ? "/by .*" : "/at .*").matcher(input).find();
        if ((isDeadline || isEvent) && hasTimeKeyword || isTodo) {
            String[] actualTask = input.replaceAll(isDeadline ? "^deadline\\s+" : isEvent ? "^event\\s+" : "^todo\\s+",
                            "").split(isDeadline ? "/by\\s+" : isEvent ? "/at\\s+" : "\n");
            String description = actualTask[0];
            String dateTime = isDeadline || isEvent ? actualTask[1] : null;
            if (isDeadline) {
                return new AddCommand(new Deadline(description, parseLocalDateTime(dateTime)));
            }
            if (isEvent) {
                return new AddCommand(new Event(description, parseLocalDateTime(dateTime)));
            }
            return new AddCommand(new Todo(description));
        }
        TaskType deadlineOrEvent = isDeadline ? TaskType.DEADLINE : TaskType.EVENT;
        String timeKeyword = isDeadline ? "/by" : "/at";
        throw new CortanaException("Please specify the " + deadlineOrEvent + " time with the "
                + timeKeyword + " keyword!");
    }
}
