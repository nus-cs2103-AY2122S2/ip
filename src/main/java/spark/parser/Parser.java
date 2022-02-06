package spark.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import spark.exceptions.SparkException;
import spark.parser.commands.CommandKeyword;
import spark.exceptions.formatexceptions.*;
import spark.parser.commands.commandtypes.*;
import spark.parser.params.AddDeadlineParams;
import spark.parser.params.AddEventParams;
import spark.parser.params.AddTodoParams;

/**
 * Holds methods for interpreting user's input.
 */
public class Parser {
    /** Specifies the accepted input-format for dates and times */
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("M-d-yyyy Hmm");

    /**
     * Returns a Command specific to the type of operation
     * that the user wishes to perform.
     *
     * @param input what the user has typed into the console
     * @return      a Command
     */
    public static Command parseInput(String input) throws SparkException {
        String[] tokens = input.split(" "); // split command into individual keywords by single-space
        String commandKeyword = tokens[0]; // assume that the first keyword is always the command word
        CommandKeyword keyword = CommandKeyword.getCommand(commandKeyword);

        switch (keyword) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(getMarkParams(input, keyword));
        case UNMARK:
            return new UnMarkCommand(getUnmarkParams(input, keyword));
        case DELETE:
            return new DeleteTaskCommand(getDeleteTaskParams(input, keyword));
        case TODO:
            return new AddTodoCommand(getAddToDoParams(input, keyword));
        case DEADLINE:
            return new AddDeadlineCommand(getAddDeadlineParams(input, keyword));
        case EVENT:
            return new AddEventCommand(getAddEventParams(input, keyword));
        case FIND:
            return new FindTaskCommand(getFindTaskParams(input, keyword));
        default:
            return new UnrecognisedCommand();
        }
    }

    private static int getMarkParams(String input, CommandKeyword keyword) throws InvalidMarkParamsException {
        String params = removeCommandKeyword(input, keyword);

        try {
            int taskIndex = Integer.parseInt(params);
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new InvalidMarkParamsException();
        }
    }

    private static int getUnmarkParams(String input, CommandKeyword keyword) throws InvalidUnmarkParamsException {
        String params = removeCommandKeyword(input, keyword);

        try {
            int taskIndex = Integer.parseInt(params);
            return taskIndex;
        } catch (NumberFormatException e) {
            throw new InvalidUnmarkParamsException();
        }
    }

    private static AddTodoParams getAddToDoParams(String input, CommandKeyword keyword) throws
            InvalidTodoParamsException {
        String title = removeCommandKeyword(input, keyword).trim();

        if (title.isBlank()) {
            throw new InvalidTodoParamsException();
        }

        return new AddTodoParams(title);
    }

    private static AddDeadlineParams getAddDeadlineParams(String input, CommandKeyword keyword)
            throws InvalidDeadlineParamsException {
        String params = removeCommandKeyword(input, keyword);
        String[] nameAndDate = params.split("/by");

        if (nameAndDate.length < 2) {
            throw new InvalidDeadlineParamsException();
        }

        String title = nameAndDate[0].trim();
        String dateTimeString = nameAndDate[1].trim();
        LocalDateTime localDateTime;

        try {
            localDateTime = LocalDateTime.parse(dateTimeString, INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineParamsException();
        }

        if (title.isBlank()) {
            throw new InvalidDeadlineParamsException();
        }

        return new AddDeadlineParams(title, localDateTime);
    }

    private static AddEventParams getAddEventParams(String input, CommandKeyword keyword)
            throws InvalidEventParamsException {
        String params = removeCommandKeyword(input, keyword);
        String[] nameAndDate = params.split("/at");

        if (nameAndDate.length < 2) {
            throw new InvalidEventParamsException();
        }

        String title = nameAndDate[0].trim();
        String dateTimeString = nameAndDate[1].trim();
        LocalDateTime localDateTime;

        try {
            localDateTime = LocalDateTime.parse(dateTimeString, INPUT_DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidEventParamsException();
        }

        if (title.isBlank()) {
            throw new InvalidEventParamsException();
        }

        return new AddEventParams(title, localDateTime);
    }

    private static String getFindTaskParams(String input, CommandKeyword keyword) throws EmptyKeywordException {
        String params = removeCommandKeyword(input, keyword);

        if (params.isBlank()) {
            throw new EmptyKeywordException();
        }

        return params;
    }

    private static int getDeleteTaskParams(String input, CommandKeyword keyword) throws EmptyKeywordException {
        String params = removeCommandKeyword(input, keyword);
        int index = Integer.parseInt(params);

        return index;
    }

    private static String removeCommandKeyword(String input, CommandKeyword keyword) {
        String withoutCommandKeyword = input.substring(keyword.getCommandKeywordLength()).trim();

        return withoutCommandKeyword;
    }
}
