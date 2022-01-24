package duke.parser;

import duke.bot.BotType;
import duke.command.*;
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

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    public static final String dateInputFormat = "dd-MM-yyyy";
    public static final String timeInputFormat = "HHmm";

    public static LocalDate convertToDate(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateInputFormat);

        return LocalDate.parse(input, formatter);
    }

    public static LocalTime convertToTime(String input) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeInputFormat);

        return LocalTime.parse(input, formatter);
    }

    private static boolean checkSingleArgs(String[] inputArgs) {
        return (inputArgs.length < 2);
    }

    public static Command parseCommand(String input) throws DukeException {
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
