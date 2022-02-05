package duke.parser;

import duke.command.*;

/**
 * Represents a parser that makes sense of the command provided by the user.
 * A <code>ParserTest</code> corresponds to a parsing tool whose method can be
 * used universally to make sense of a user input.
 */
public class Parser {

    /**
     * Returns the command based on the input of the user. If the command is
     * not valid, it returns a <code>HelpCommand</code>, else it returns one
     * that has specific execution instructions that prompt and guide the bot
     * towards specific execution, actions, and behaviours..
     *
     * @param fullCommand The input provided by the user using the CLI.
     * @return the specific command generated by the user input.
     */
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

        case FindCommand.COMMAND_WORDS:
            return new FindCommand(arguments.toString().split(" ")[0]);

        default:
            return new HelpCommand();
        }

    }
}
