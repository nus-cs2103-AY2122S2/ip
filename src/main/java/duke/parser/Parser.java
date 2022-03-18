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
import duke.command.TagCommand;
import duke.exception.DukeCommandException;
import duke.exception.DukeDateTimeFormatException;
import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeMissingArgumentException;
import duke.exception.DukeNumberFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;


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
     * @throws DukeNumberFormatException Thrown when Integer class parseInt throws a NumberFormatException.
     */
    public static int parseInt(String input) throws DukeNumberFormatException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeNumberFormatException();
        }
    }

    /**
     * Parses a String input into a LocalDateTime object.
     *
     * @param input String Date input.
     * @return LocalDateTime object.
     * @throws DukeDateTimeFormatException Thrown when LocalDateTime parse throws a DateTimeParseException.
     */
    public static LocalDateTime parseDateTime(String input) throws DukeDateTimeFormatException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DukeDateTimeFormatException();
        }
    }

    private static Command parseAddCommand(String inputCommand, Command.CommandType addType, Ui ui)
            throws DukeException {
        String[] inputArray = inputCommand.split(" ");
        String content;
        String dateTimeString;
        // Represents the index of \by or \at.
        int indexOfDateTime;
        if (addType.equals(Command.CommandType.TODO)) {
            content = inputCommand.substring(5);
            return new AddCommand(new ToDo(content, ui), inputArray);
        } else if (addType.equals(Command.CommandType.EVENT)) {
            indexOfDateTime = inputCommand.lastIndexOf(" \\at ");
            content = inputCommand.substring(Command.CommandType.EVENT.name().length() + 1, indexOfDateTime);
            dateTimeString = inputCommand.substring(indexOfDateTime + 5);
            LocalDateTime dateTime = parseDateTime(dateTimeString);
            return new AddCommand(new Event(content, dateTime, ui), inputArray);
        } else if (addType.equals(Command.CommandType.DEADLINE)) {
            indexOfDateTime = inputCommand.lastIndexOf(" \\by ");
            content = inputCommand.substring(Command.CommandType.DEADLINE.name().length() + 1, indexOfDateTime);
            dateTimeString = inputCommand.substring(indexOfDateTime + 5);
            LocalDateTime dateTime = parseDateTime(dateTimeString);
            return new AddCommand(new Deadline(content, dateTime, ui), inputArray);
        } else {
            throw new DukeCommandException(inputCommand);
        }
    }

    private static Command parseDeleteCommand(String inputCommand, Ui ui) throws DukeException {
        String[] inputArray = inputCommand.split(" ");
        if (inputArray.length > 2) {
            throw new DukeInvalidArgumentException("Too many arguments!");
        }
        int deleteIndex = parseInt(inputArray[1]);
        return new DeleteCommand(deleteIndex, inputArray);
    }

    private static Command parseMarkCommand(String inputCommand, MarkCommand.Mark markType, Ui ui)
            throws DukeException {
        String[] inputArray = inputCommand.split(" ");
        if (inputArray.length > 2) {
            throw new DukeInvalidArgumentException("Too many arguments!");
        }
        int markIndex = parseInt(inputArray[1]);
        if (markType.equals(MarkCommand.Mark.MARK)) {
            return new MarkCommand(markIndex, MarkCommand.Mark.MARK, inputArray);
        } else if (markType.equals(MarkCommand.Mark.UNMARK)) {
            return new MarkCommand(markIndex, MarkCommand.Mark.UNMARK, inputArray);
        } else {
            throw new DukeInvalidArgumentException(inputArray[0]);
        }
    }

    private static Command parseFindCommand(String inputCommand, Ui ui) {
        String[] inputArray = inputCommand.split(" ");
        String target = inputCommand.substring(Command.CommandType.FIND.name().length() + 1);
        return new FindCommand(target, inputArray);
    }

    private static Command parseSortCommand(String inputCommand, Ui ui) throws DukeException {
        String[] inputArray = inputCommand.split(" ");
        if (inputArray.length > 2) {
            throw new DukeInvalidArgumentException(inputArray[2]);
        }
        String sortType = inputCommand.substring(Command.CommandType.SORT.name().length() + 1);
        if (TaskList.SortType.CONTENT.equals(sortType)) {
            return new SortCommand(TaskList.SortType.CONTENT, inputArray);
        } else if (TaskList.SortType.DATE.equals(sortType)) {
            return new SortCommand(TaskList.SortType.DATE, inputArray);
        } else {
            throw new DukeInvalidArgumentException("sort type");
        }
    }

    private static Command parseTagCommand(String inputCommand, Command.CommandType tagType, Ui ui)
            throws DukeException {
        String[] inputArray = inputCommand.split(" ");
        if (inputArray.length == 2) {
            throw new DukeInvalidArgumentException("Too few arguments!");
        } else if (inputArray.length > 3) {
            throw new DukeInvalidArgumentException("Too many arguments!");
        }

        if (tagType.equals(Command.CommandType.TAG)) {
            String stringIndex = inputArray[1];
            String name = inputArray[2];
            int index = parseInt(stringIndex);
            return new TagCommand(index, TagCommand.TagType.TAG, name);
        } else if (tagType.equals(Command.CommandType.UNTAG)) {
            String stringIndex = inputArray[1];
            String name = inputArray[2];
            int index = parseInt(stringIndex);
            return new TagCommand(index, TagCommand.TagType.UNTAG, name);
        } else {
            throw new DukeCommandException(inputArray[0]);
        }
    }

    /**
     * Parses the inputCommand into a Command object.
     *
     * @param inputCommand String of command.
     * @return Command associated with the input command.
     * @throws DukeException DukeException regarding the input command.
     */
    private static Command parseCommand(String inputCommand, Ui ui) throws DukeException {
        String[] inputArray = inputCommand.split(" ");
        String firstArg = inputArray[0];
        if (!Command.CommandType.isCommandType(firstArg)) {
            throw new DukeCommandException(firstArg);
        }
        if (inputArray.length == 1) {
            if (Command.CommandType.BYE.equals(firstArg)) {
                return new ByeCommand(inputArray);
            } else if (Command.CommandType.LIST.equals(firstArg)) {
                return new ListCommand(inputArray);
            } else {
                throw new DukeMissingArgumentException(firstArg);
            }
        } else {
            if (Command.CommandType.BYE.equals(firstArg)) {
                throw new DukeInvalidArgumentException("Too many arguments!");
            } else if (Command.CommandType.LIST.equals(firstArg)) {
                throw new DukeInvalidArgumentException("Too many arguments");
            } else if (Command.CommandType.TODO.equals(firstArg)) {
                return parseAddCommand(inputCommand, Command.CommandType.TODO, ui);
            } else if (Command.CommandType.EVENT.equals(firstArg)) {
                return parseAddCommand(inputCommand, Command.CommandType.EVENT, ui);
            } else if (Command.CommandType.DEADLINE.equals(firstArg)) {
                return parseAddCommand(inputCommand, Command.CommandType.DEADLINE, ui);
            } else if (Command.CommandType.DELETE.equals(firstArg)) {
                return parseDeleteCommand(inputCommand, ui);
            } else if (Command.CommandType.FIND.equals(firstArg)) {
                return parseFindCommand(inputCommand, ui);
            } else if (Command.CommandType.MARK.equals(firstArg)) {
                return parseMarkCommand(inputCommand, MarkCommand.Mark.MARK, ui);
            } else if (Command.CommandType.UNMARK.equals(firstArg)) {
                return parseMarkCommand(inputCommand, MarkCommand.Mark.UNMARK, ui);
            } else if (Command.CommandType.SORT.equals(firstArg)) {
                return parseSortCommand(inputCommand, ui);
            } else if (Command.CommandType.TAG.equals(firstArg)) {
                return parseTagCommand(inputCommand, Command.CommandType.TAG, ui);
            } else if (Command.CommandType.UNTAG.equals(firstArg)) {
                return parseTagCommand(inputCommand, Command.CommandType.UNTAG, ui);
            } else {
                return new InvalidCommand();
            }
        }
    }

    /**
     * Parses a given inputCommand into a Command object for the ease of interpreting commands.
     *
     * @param inputCommand String String of input command.
     * @return Command Returns any subclass of Command based on the input String.
     * @throws DukeException
     */
    public static Command parse(String inputCommand, Ui ui) throws DukeException {
        if (inputCommand.trim().length() == 0) {
            throw new DukeCommandException("");
        }
        return parseCommand(inputCommand, ui);
    }
}
