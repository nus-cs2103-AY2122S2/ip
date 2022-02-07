package duke.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteAllCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.ShowAllTasksOnSameDateCommand;
import duke.command.UnmarkCommand;
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
        boolean isMarkCommand = input.toLowerCase().matches("^mark \\d+|^mark -\\d+");
        boolean isUnmarkCommand = input.toLowerCase().matches("^unmark \\d+|^unmark -\\d+");
        boolean isDeleteCommand = input.toLowerCase().matches("^delete \\d+|^delete -\\d+");
        boolean isDeleteAllCommand = input.toLowerCase().matches("^delete all");
        boolean isShowAllOnSameDate = input.toLowerCase()
                .matches("^show all( )?(\\d{4} ?.?/?-?\\d{1,2} ?.?/?-?\\d{1,2})?( )?(\\d{4})?");
        boolean isFindCommand = input.toLowerCase().matches("^find( )? .*");
        boolean isEmptyDeadline = input.toLowerCase().replaceAll("[ |\\t]", "").equals("deadline");
        boolean isEmptyEvent = input.toLowerCase().replaceAll("[ |\\t]", "").equals("event");
        boolean isEmptyTodo = input.toLowerCase().replaceAll("[ |\\t]", "").equals("todo");
        boolean isDeadlineWithDescription = input.toLowerCase().matches("^deadline .*");
        boolean isEventWithDescription = input.toLowerCase().matches("^event .*");
        boolean isTodoWithDescription = input.toLowerCase().matches("^todo .*");

        if (isExit) {
            return new ExitCommand();
        }
        if (isListCommand) {
            return new ListCommand();
        }
        if (isMarkCommand) {
            int index = Integer.parseInt(input.replaceAll("mark ", "")) - 1;
            return new MarkCommand(index);
        }
        if (isUnmarkCommand) {
            int index = Integer.parseInt(input.replaceAll("unmark ", "")) - 1;
            return new UnmarkCommand(index);
        }
        if (isDeleteCommand) {
            int index = Integer.parseInt(input.replaceAll("delete ", "")) - 1;
            return new DeleteCommand(index);
        }
        if (isDeleteAllCommand) {
            return new DeleteAllCommand();
        }
        if (isShowAllOnSameDate) {
            boolean isWithoutDateTime = input.replaceAll(" ", "").matches("showall");
            if (isWithoutDateTime) {
                /* user did not specify time */
                throw new CortanaException("Please specify the date time you are looking for!");
            }
            String dateTimeString = input.replaceAll("show all ", "");
            return new ShowAllTasksOnSameDateCommand(parseLocalDateTime(dateTimeString), dateTimeString);
        }
        if (isFindCommand) {
            String keyWord = input.replaceAll("find ", "");
            if (keyWord.isEmpty()) {
                throw new CortanaException("Please specify the keyword you are looking for!");
            } else {
                return new FindCommand(keyWord);
            }
        }

        if (isEmptyDeadline || isEmptyEvent || isEmptyTodo) {
            /* user does not specify task description*/
            String aOrAn = isEmptyEvent ? "an " : "a ";
            TaskType taskType = isEmptyDeadline ? TaskType.DEADLINE : isEmptyEvent ? TaskType.EVENT : TaskType.TODO;
            throw new CortanaException("OOPS!!! The description of " + aOrAn + taskType
                    + " cannot be empty!");
        }

        boolean hasBy = Pattern.compile("/by .*").matcher(input).find();
        boolean hasAt = Pattern.compile("/at .*").matcher(input).find();
        String description;
        String time;

        if (isDeadlineWithDescription && hasBy) {
            /* valid deadline command */
            String[] actualTask = input.replaceAll("deadline ", "")
                    .split("/by ");
            description = actualTask[0];
            time = actualTask[1];
            Deadline deadline = new Deadline(description, parseLocalDateTime(time));
            return new AddCommand(deadline);
        } else if (isEventWithDescription && hasAt) {
            /* valid event command */
            String[] actualTask = input.replaceAll("event ", "").split("/at ");
            description = actualTask[0];
            time = actualTask[1];
            Event event = new Event(description, parseLocalDateTime(time));
            return new AddCommand(event);
        } else if (isTodoWithDescription) {
            /* valid todo command */
            description = input.replaceAll("todo ", "");
            Todo todo = new Todo(description);
            return new AddCommand(todo);
        } else if (isDeadlineWithDescription) {
            /* deadline without specifying time with /by */
            throw new CortanaException("Please specify the deadline time with the /by keyword!");
        } else if (isEventWithDescription) {
            /* event without specifying time with /at */
            throw new CortanaException("Please specify the event time with the /at keyword!");
        } else {
            /* invalid command */
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
}
