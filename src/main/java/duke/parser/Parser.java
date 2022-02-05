package duke.parser;

import duke.command.AddCommand;
import duke.command.Commands;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

public class Parser {

    public static Commands parse(String fullCommand) {
        StringBuilder arguments = new StringBuilder("");
        fullCommand = fullCommand.trim();
        String commandWord = fullCommand.split(" ", 2)[0].trim();
        if (fullCommand.split(" ", 2).length > 1) {
            arguments.append(fullCommand.split(" ", 2)[1].trim());
        }

        switch (commandWord) {

        //Fallthrough
        case AddCommand.COMMAND_TODO:

        //Fallthrough
        case AddCommand.COMMAND_EVENT:

        //Fallthrough
        case AddCommand.COMMAND_DEADLINE:
            return new AddCommand(commandWord,
                    arguments.toString());

        //Fallthrough
        case ExitCommand.COMMAND_WORDS:
            return new ExitCommand();

        //Fallthrough
        case ListCommand.COMMAND_WORDS:
            return new ListCommand();

        //Fallthrough
        case MarkCommand.COMMAND_WORDS:
            return new MarkCommand(arguments.toString());

        //Fallthrough
        case UnmarkCommand.COMMAND_WORDS:
            return new UnmarkCommand(arguments.toString());

        //Fallthrough
        case DeleteCommand.COMMAND_WORDS:
            return new DeleteCommand(arguments.toString());

        default:
            return new HelpCommand();
        }

    }
}
