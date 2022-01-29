package Main;

import Command.Command;
import Command.AddCommand;
import Command.ExitCommand;
import Command.ListCommand;
import Command.MarkCommand;
import Command.DeleteCommand;
import Command.FindCommand;

public class Parser {

    static public Command parse(String s) throws TsundereException, NumberFormatException {
        String[] splitStr;
        int num;

        String us = s.toUpperCase();

        if (us.contains("BYE")) {
            return new ExitCommand();
        } else if (us.contains("LIST")) {
            return new ListCommand();
        } else if (us.contains("UNMARK")) {
            splitStr = s.split(" ");
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example: unmark 1");
            }
            num = Integer.parseInt(splitStr[1]);
            return new MarkCommand("UNMARK", num);
        } else if (us.contains("MARK")) {
            splitStr = s.split(" ");
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example: mark 1");
            }
            num = Integer.parseInt(splitStr[1]);
            return new MarkCommand("MARK", num);
        } else if (us.contains("TODO")) {
            splitStr = s.split(" ", 2);
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example, todo sleep");
            }
            return new AddCommand("TODO", splitStr[1]);
        } else if (us.contains("DEADLINE")) {
            splitStr = s.split(" ", 2);
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example, deadline sleep/by 2019-10-15");
            }
            return new AddCommand("DEADLINE", splitStr[1]);
        } else if (us.contains("EVENT")) {
            splitStr = s.split(" ", 2);
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example, event sleep/at 2019-10-15");
            }
            return new AddCommand("EVENT", splitStr[1]);
        } else if (us.contains("DELETE")) {
            splitStr = s.split(" ");
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example: delete 1");
            }
            num = Integer.parseInt(splitStr[1]);
            return new DeleteCommand(num);
        } else if (us.contains("FIND")) {
            splitStr = s.split(" ");
            if (splitStr.length < 2) {
                throw new TsundereException("Hmph you baka, gimme a correct format. For example: find eat");
            }
            return new FindCommand(splitStr[1]);
        } else {
            throw new TsundereException("I don't know what you want! Say something valid.");
        }
    }
}

