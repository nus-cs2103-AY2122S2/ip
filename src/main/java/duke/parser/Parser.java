package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.exception.DukeCommandException;
import duke.exception.DukeDateTimeFormatException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeNumberFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;


/**
 * Parses input String into a more decipherable object.
 */
public class Parser {
    /**
     * Formats an input String into a LocalDateTime object.
     */
    public static final DateTimeFormatter INPUT_FORMATTER = new DateTimeFormatterBuilder()
            .parseCaseInsensitive()
            .appendPattern("yyyy-MM-dd HHmm")
            .toFormatter(Locale.ENGLISH);

    /**
     * Parses a String to an int, but throws DukeNumberFormatException instead.
     *
     * @return int Integer value of the String.
     */
    private static int parseInt(String input) throws DukeNumberFormatException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeNumberFormatException();
        }
    }

    private static LocalDateTime parseDateTime(String input) throws DukeDateTimeFormatException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeFormatException();
        }
    }

    /**
     * Parses a given inputCommand into a Command object for the ease of interpreting commands.
     *
     * @param inputCommand String String of input command.
     * @return Command Returns any subclass of Command based on the input String.
     * @throws DukeException
     */
    public static Command parse(String inputCommand) throws DukeException {
        if (inputCommand.trim().length() == 0) {
            throw new DukeCommandException("");
        }
        String[] inputArray = inputCommand.split(" ");
        String firstArg = inputArray[0];
        if (inputArray.length == 1) {
            if (Command.CommandType.BYE.equals(firstArg)) {
                return new ByeCommand(inputArray);
            } else if (Command.CommandType.LIST.equals(firstArg)) {
                return new ListCommand(inputArray);
            } else {
                throw new DukeCommandException(firstArg);
            }
        } else {
            String secondArg = inputArray[1];
            if (Command.CommandType.FIND.equals(firstArg)) {
                String target = inputCommand.substring(5);
                if (!target.isEmpty()) {
                    return new FindCommand(target, inputArray);
                } else {
                    throw new DukeMissingArgumentException("target");
                }
            } else if (Command.CommandType.DELETE.equals(firstArg)) {
                int index = parseInt(secondArg);
                return new DeleteCommand(index, inputArray);
            } else if (Command.CommandType.DEADLINE.equals(firstArg)) {
                if (secondArg.equals("\\by")) {
                    throw new DukeMissingArgumentException("task description");
                }
                String content = "";
                int indexOfBy = inputCommand.lastIndexOf("\\by ");
                if (indexOfBy == -1) {
                    throw new DukeMissingArgumentException("\\by deadlineTime");
                } else {
                    String by = inputCommand.substring(indexOfBy + 4);
                    content = inputCommand.substring(9, indexOfBy - 1);
                    LocalDateTime date = null;
                    date = parseDateTime(by);
                    Task taskObj = new Deadline(content, date);
                    return new AddCommand(taskObj, inputArray);
                }
            } else if (Command.CommandType.EVENT.equals(firstArg)) {
                if (secondArg.equals("\\by")) {
                    throw new DukeMissingArgumentException("task description");
                }
                String content = "";
                int indexOfAt = inputCommand.lastIndexOf("\\at ");
                if (indexOfAt == -1) {
                    throw new DukeMissingArgumentException("\\at eventTime");
                } else {
                    String by = inputCommand.substring(indexOfAt + 4);
                    content = inputCommand.substring(6, indexOfAt - 1);
                    LocalDateTime date = null;
                    date = parseDateTime(by);
                    Task taskObj = new Event(content, date);
                    return new AddCommand(taskObj, inputArray);
                }
            } else if (Command.CommandType.TODO.equals(firstArg)) {
                String content = "";
                for (int k = 1; k < inputArray.length; k++) {
                    content += inputArray[k];
                }
                Task taskObj = new ToDo(content);
                return new AddCommand(taskObj, inputArray);
            } else if (Command.CommandType.MARK.equals(firstArg)) {
                int index = parseInt(secondArg);
                return new MarkCommand(index, MarkCommand.Mark.MARK, inputArray);
            } else if (Command.CommandType.UNMARK.equals(firstArg)) {
                int index = parseInt(secondArg);
                return new MarkCommand(index, MarkCommand.Mark.UNMARK, inputArray);
            } else if (Command.CommandType.SORT.equals(firstArg)) {
                if (TaskList.SortType.CONTENT.equals(secondArg)) {
                    return new SortCommand(TaskList.SortType.CONTENT, inputArray);
                } else if (TaskList.SortType.DATE.equals(secondArg)) {
                    return new SortCommand(TaskList.SortType.DATE, inputArray);
                } else {
                    throw new DukeInvalidArgumentException("sort type");
                }
            } else {
                return new InvalidCommand(inputCommand);
            }
        }
    }
}
