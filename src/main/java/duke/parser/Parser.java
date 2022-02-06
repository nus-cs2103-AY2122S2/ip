package duke.parser;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.bot.BotType;
import duke.command.BotCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.add.DeadlineCommand;
import duke.command.add.EventCommand;
import duke.command.add.TodoCommand;
import duke.command.index.DeleteCommand;
import duke.command.index.MarkCommand;
import duke.exception.DukeException;
import duke.exception.InvalidArgumentException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Represents a system to translate user input into respective objects.
 * <code>Parser</code> class methods are static, cannot be created and
 * is used to convert String of user input into its intended object.
 */
public class Parser {
    public static final String DATE_INPUT_FORMAT = "dd-MM-yyyy";
    public static final String TIME_INPUT_FORMAT = "HHmm";

    /**
     * Returns a LocalDate object specified by the user input.
     * @param input date input.
     * @return a LocalDate object of the specified date.
     */
    public static LocalDate convertToDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_INPUT_FORMAT);

        return LocalDate.parse(input, formatter);
    }

    /**
     * Returns a LocalTime object specified by the user input.
     * @param input time input.
     * @return a LocalTime object of the specified time.
     */
    public static LocalTime convertToTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(TIME_INPUT_FORMAT);

        return LocalTime.parse(input, formatter);
    }

    /**
     * Returns a Command object based on the user input.
     *
     * @param input user command input.
     * @return a command object.
     * @throws DukeException if command format is invalid.
     */
    public static Command parseCommand(String input) throws DukeException {

        assert !input.isBlank() : "Input should not be empty/blank";

        String[] inputArgs = input.trim().split(" ", 2);

        switch (inputArgs[0]) {
        case TodoCommand.COMMAND_WORD:
            return handleTodo(inputArgs);
        case DeadlineCommand.COMMAND_WORD:
            return handleDeadline(inputArgs);
        case EventCommand.COMMAND_WORD:
            return handleEvent(inputArgs);
        case DeleteCommand.COMMAND_WORD:
            return handleDelete(inputArgs);
        case MarkCommand.COMMAND_WORD_MARK:
            return handleMark(inputArgs, true);
        case MarkCommand.COMMAND_WORD_UNMARK:
            return handleMark(inputArgs, false);
        case ListCommand.COMMAND_WORD:
            return handleList(inputArgs);
        case FindCommand.COMMAND_WORD:
            return handleFind(inputArgs);
        case ExitCommand.COMMAND_WORD:
            return handleExit(inputArgs);
        case BotCommand.COMMAND_WORD_JJBA:
            return handleBot(inputArgs, BotType.JJBA);
        case BotCommand.COMMAND_WORD_DIO:
            return handleBot(inputArgs, BotType.DIO);
        default:
            throw new InvalidArgumentException();
        }
    }

    private static boolean checkSingleArgs(String[] inputArgs) {
        return (inputArgs.length < 2);
    }


    private static Command handleTodo(String[] inputArgs) throws InvalidArgumentException {
        if (checkSingleArgs(inputArgs)) {
            throw new InvalidArgumentException(TodoCommand.COMMAND_FORMAT);
        }

        return new TodoCommand(new Todo(inputArgs[1]));
    }

    private static Command handleDeadline(String[] inputArgs) throws InvalidArgumentException {
        if (checkSingleArgs(inputArgs)) {
            throw new InvalidArgumentException(DeadlineCommand.COMMAND_FORMAT);
        }

        String[] commandArgs = inputArgs[1].split("/", 2);

        if (checkSingleArgs(commandArgs) || !commandArgs[1].startsWith("by")) {
            throw new InvalidArgumentException(DeadlineCommand.COMMAND_FORMAT);
        }

        String[] dateTimeArgs = commandArgs[1].substring(3).split(" ");

        try {
            Deadline newDeadline = new Deadline(commandArgs[0].trim(),
                    convertToDate(dateTimeArgs[0]), convertToTime(dateTimeArgs[1]));

            return new DeadlineCommand(newDeadline);

        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException(DeadlineCommand.COMMAND_FORMAT);
        }
    }

    private static Command handleEvent(String[] inputArgs) throws InvalidArgumentException {
        if (checkSingleArgs(inputArgs)) {
            throw new InvalidArgumentException(EventCommand.COMMAND_FORMAT);
        }

        String[] commandArgs = inputArgs[1].split("/");

        if (commandArgs.length < 2 || commandArgs[1].isBlank() || !commandArgs[1].startsWith("at")) {
            throw new InvalidArgumentException(EventCommand.COMMAND_FORMAT);
        }

        String[] dateTimeArgs = commandArgs[1].substring(3).split(" ");

        String[] timeArgs = dateTimeArgs[1].split("-");

        try {
            Event newEvent = new Event(commandArgs[0].trim(), convertToDate(dateTimeArgs[0]),
                    convertToTime(timeArgs[0]), convertToTime(timeArgs[1]));

            return new EventCommand(newEvent);

        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException(EventCommand.COMMAND_FORMAT);
        }
    }

    private static Command handleDelete(String[] inputArgs) throws InvalidArgumentException {
        if (checkSingleArgs(inputArgs)) {
            throw new InvalidArgumentException(DeleteCommand.COMMAND_FORMAT);
        }

        try {
            int index = Integer.parseInt(inputArgs[1]) - 1;

            return new DeleteCommand(index);

        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(DeleteCommand.COMMAND_FORMAT);
        }
    }

    private static Command handleMark(String[] inputArgs, boolean isMark) throws InvalidArgumentException {
        if (checkSingleArgs(inputArgs)) {
            throw new InvalidArgumentException(MarkCommand.getFormat(isMark));
        }

        try {
            int index = Integer.parseInt(inputArgs[1]) - 1;

            return new MarkCommand(index, isMark);

        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(MarkCommand.getFormat(isMark));
        }
    }

    private static Command handleList(String[] inputArgs) throws InvalidArgumentException {
        if (!checkSingleArgs(inputArgs)) {
            throw new InvalidArgumentException(ListCommand.COMMAND_FORMAT);
        }

        return new ListCommand();
    }

    private static Command handleFind(String[] inputArgs) throws InvalidArgumentException {
        if (checkSingleArgs(inputArgs)) {
            throw new InvalidArgumentException(FindCommand.COMMAND_FORMAT);
        }

        return new FindCommand(inputArgs[1]);
    }


    private static Command handleBot(String[] inputArgs, BotType botType) throws InvalidArgumentException {
        if (!checkSingleArgs(inputArgs)) {
            throw new InvalidArgumentException();
        }

        return new BotCommand(botType);
    }

    private static Command handleExit(String[] inputArgs) throws InvalidArgumentException {
        if (!checkSingleArgs(inputArgs)) {
            throw new InvalidArgumentException(ExitCommand.COMMAND_FORMAT);
        }

        return new ExitCommand();
    }


}
