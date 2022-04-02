package tsundere;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;
import command.RemindCommand;

/**
 * Parses input into commands.
 */
public class Parser {

    private static final int MIN_SPACE_NUM = 2;
    private static final String INSULT = "Hmph you baka, gimme a correct format. For example: ";

    /**
     * Parse input into commands by look at keywords.
     *
     * @param s input string by the users.
     * @return commands for execution.
     * @throws TsundereException for incorrect input string format.
     * @throws NumberFormatException for incorrect input integer format.
     */
    public static Command parse(String s) throws TsundereException, NumberFormatException {

        String us = s.toUpperCase();

        if (us.contains("BYE")) {
            return new ExitCommand();
        } else if (us.contains("LIST")) {
            return new ListCommand();
        } else if (us.contains("REMIND")) {
            return new RemindCommand();
        } else if (us.contains("UNMARK")) {
            String commandArgs = checkFormat(s, " ", INSULT + "unmark 1");
            int num = Integer.parseInt(commandArgs);
            return new MarkCommand("UNMARK", num);
        } else if (us.contains("MARK")) {
            String commandArgs = checkFormat(s, " ", INSULT + "mark 1");
            int num = Integer.parseInt(commandArgs);
            return new MarkCommand("MARK", num);
        } else if (us.contains("TODO")) {
            String commandArgs = checkFormat(s, " ", INSULT + "todo sleep");
            return new AddCommand("TODO", commandArgs);
        } else if (us.contains("DEADLINE")) {
            String commandArgs = checkFormat(s, " ", INSULT + "deadline sleep/by 2019-10-15");
            return new AddCommand("DEADLINE", commandArgs);
        } else if (us.contains("EVENT")) {
            String commandArgs = checkFormat(s, " ", INSULT + "event sleep/at 2019-10-15");
            return new AddCommand("EVENT", commandArgs);
        } else if (us.contains("DELETE")) {
            String commandArgs = checkFormat(s, " ", INSULT + "delete 1");
            int num = Integer.parseInt(commandArgs);
            return new DeleteCommand(num);
        } else if (us.contains("FIND")) {
            String commandArgs = checkFormat(s, " ", INSULT + "find eat");
            return new FindCommand(commandArgs);
        } else {
            throw new TsundereException("I don't know what you want! Say something valid.");
        }
    }

    /**
     * Check if the input format is correct then return first argument in the command.
     *
     * @param input Input command by user.
     * @param toSplit What to split the string by.
     * @param errorOutput What to say when there is an format error.
     * @return first argument in the command
     * @throws TsundereException When there is a format error.
     */
    public static String checkFormat(String input, String toSplit, String errorOutput) throws TsundereException {
        String[] splitStr;
        splitStr = input.split(toSplit, 2);
        if (splitStr.length < MIN_SPACE_NUM) {
            throw new TsundereException(errorOutput);
        }
        return splitStr[1];
    }
}

