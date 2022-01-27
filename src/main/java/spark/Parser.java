package spark;

import spark.commands.commandtypes.*;
import spark.commands.CommandKeyword;
import spark.exceptions.SparkException;
import spark.exceptions.formatexceptions.*;

import java.nio.InvalidMarkException;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Holds methods for interpreting user's input.
 */
public class Parser {
    /**
     * Returns a Command specific to the type of operation
     * that the user wishes to perform.
     *
     * @param input
     * @return
     */
    public static Command parseInput(String input) throws SparkException {
        String[] tokens = input.split(" "); // split command into individual keywords by single-space
        String commandKeyword = tokens[0]; // assume that the first keyword is always the command word
        CommandKeyword keyword = CommandKeyword.getCommand(commandKeyword);

        if (keyword == CommandKeyword.BYE) {
            return new ExitCommand();

        } else if (keyword == CommandKeyword.LIST) {
            return new ListCommand();

        } else if (keyword == CommandKeyword.MARK) {
            return new MarkCommand(getMarkParams(input, keyword));

        } else if (keyword == CommandKeyword.UNMARK) {
            return new UnMarkCommand(getUnmarkParams(input, keyword));

        } else if (keyword == CommandKeyword.DELETE) {
            return new DeleteTaskCommand(getDeleteTaskParams(input, keyword));

        } else if (keyword == CommandKeyword.TODO) {
            String name = getAddToDoParams(input, keyword);
            return new AddToDoCommand(name.trim());

        } else if (keyword == CommandKeyword.DEADLINE) {
            String[] nameAndDate = getAddDeadlineParams(input, keyword);
            return new AddDeadlineCommand(nameAndDate[0].trim(), nameAndDate[1].trim());

        } else if (keyword == CommandKeyword.EVENT) {
            String[] nameAndDate = getAddEventParams(input, keyword);
            return new AddEventCommand(nameAndDate[0].trim(), nameAndDate[1].trim());

        } else if (keyword == CommandKeyword.FIND) {
            return new FindTaskCommand(getFindTaskParams(input, keyword));

        } else {
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

    private static String getAddToDoParams(String input, CommandKeyword keyword) throws
            InvalidToDoParamsException {
        String params = removeCommandKeyword(input, keyword);

        if (params.isBlank()) {
            throw new InvalidToDoParamsException();
        }

        return params;
    }

    private static String[] getAddDeadlineParams(String input, CommandKeyword keyword)
            throws InvalidDeadlineParamsException {
        String params = removeCommandKeyword(input, keyword);
        String[] nameAndDate = params.split("/by");

        if (nameAndDate.length < 2) {
            throw new InvalidDeadlineParamsException();
        }

        if (nameAndDate[0].isBlank()) {
            throw new InvalidDeadlineParamsException();
        }

        return nameAndDate;
    }

    private static String[] getAddEventParams(String input, CommandKeyword keyword)
            throws InvalidEventParamsException {
        String params = removeCommandKeyword(input, keyword);
        String[] nameAndDate = params.split("/at");

        if (nameAndDate.length < 2) {
            throw new InvalidEventParamsException();
        }

        if (nameAndDate[0].isBlank()) {
            throw new InvalidEventParamsException();
        }

        return nameAndDate;
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
