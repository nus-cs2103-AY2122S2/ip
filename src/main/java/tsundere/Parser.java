package tsundere;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkCommand;

/**
 * Parses input into commands.
 */
public class Parser {

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
        } else if (us.contains("UNMARK")) {
            String[] splitStr = s.split(" ");
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example: unmark 1");
            }
            int num = Integer.parseInt(splitStr[1]);
            return new MarkCommand("UNMARK", num);
        } else if (us.contains("MARK")) {
            String[] splitStr = s.split(" ");
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example: mark 1");
            }
            int num = Integer.parseInt(splitStr[1]);
            return new MarkCommand("MARK", num);
        } else if (us.contains("TODO")) {
            String[] splitStr = s.split(" ", 2);
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example, todo sleep");
            }
            return new AddCommand("TODO", splitStr[1]);
        } else if (us.contains("DEADLINE")) {
            String[] splitStr = s.split(" ", 2);
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. "
                        + "For example, deadline sleep/by 2019-10-15");
            }
            return new AddCommand("DEADLINE", splitStr[1]);
        } else if (us.contains("EVENT")) {
            String[] splitStr = s.split(" ", 2);
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. "
                        + "For example, event sleep/at 2019-10-15");
            }
            return new AddCommand("EVENT", splitStr[1]);
        } else if (us.contains("DELETE")) {
            String[] splitStr = s.split(" ");
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. "
                        + "For example: delete 1");
            }
            int num = Integer.parseInt(splitStr[1]);
            return new DeleteCommand(num);
        } else if (us.contains("FIND")) {
            String[] splitStr = s.split(" ");
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. "
                        + "For example: find eat");
            }
            return new FindCommand(splitStr[1]);
        } else {
            throw new TsundereException("I don't know what you want! Say something valid.");
        }
    }
}

