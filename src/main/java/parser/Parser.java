package parser;

import command.AddCommand;
import command.Commands;
import command.DeleteCommand;
import command.ExitCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;

import parser.Parser;

public class Parser {

    public static Commands parse(String fullCommand) {
        StringBuilder arguments = new StringBuilder("");
        fullCommand = fullCommand.trim();
        String commandWord = fullCommand.split(" ", 2)[0].trim();
        if (fullCommand.split(" ", 2).length > 1) {
            arguments.append(fullCommand.split(" ", 2)[1].trim());
        }

        switch (commandWord) {
            case AddCommand.COMMAND_TODO:
                return new AddCommand(commandWord, arguments.toString());

            case AddCommand.COMMAND_EVENT:
                return new AddCommand(commandWord, arguments.toString());

            case AddCommand.COMMAND_DEADLINE:
                return new AddCommand(commandWord, arguments.toString());

            case ExitCommand.COMMAND_WORDS:
                return new ExitCommand();

            case ListCommand.COMMAND_WORDS:
                return new ListCommand();

            case MarkCommand.COMMAND_WORDS:
                return new MarkCommand(arguments.toString());

            case UnmarkCommand.COMMAND_WORDS:
                return new UnmarkCommand(arguments.toString());

            case DeleteCommand.COMMAND_WORDS:
                return new DeleteCommand(arguments.toString());

            default:
                return new HelpCommand();
        }

    }
}
