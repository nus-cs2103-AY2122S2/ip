package sparrow.logic.parser;

import sparrow.commons.exceptions.ParseException;
import sparrow.logic.commands.ByeCommand;
import sparrow.logic.commands.Command;
import sparrow.logic.commands.DeadlineCommand;
import sparrow.logic.commands.DeleteCommand;
import sparrow.logic.commands.EventCommand;
import sparrow.logic.commands.FindCommand;
import sparrow.logic.commands.HelpCommand;
import sparrow.logic.commands.ListCommand;
import sparrow.logic.commands.MarkCommand;
import sparrow.logic.commands.PrioritiseCommand;
import sparrow.logic.commands.ToDoCommand;
import sparrow.logic.commands.UnmarkCommand;

/**
 * Represents the parser for commands.
 */
public class CommandParser {
    private static final String HELP_MESSAGE = "Enter help for a list of commands.";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command.\n" + HELP_MESSAGE;

    /**
     * Parses the user input into a command for execution.
     *
     * @param userInput the user input.
     * @return A command for execution.
     * @throws ParseException when the command format is invalid.
     */
    public static Command parse(String userInput) throws ParseException {
        final String command = ParserUtil.parseCommand(userInput);
        final String args = ParserUtil.parseArgs(userInput);
        switch (command) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case DeadlineCommand.COMMAND_WORD:
            return new DeadlineCommandParser().parse(args);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(args);
        case EventCommand.COMMAND_WORD:
            return new EventCommandParser().parse(args);
        case FindCommand.COMMAND_WORD:
            return new FindCommand(args);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return new MarkCommandParser().parse(args);
        case PrioritiseCommand.COMMAND_WORD:
            return new PrioritiseCommandParser().parse(args);
        case ToDoCommand.COMMAND_WORD:
            return new ToDoCommandParser().parse(args);
        case UnmarkCommand.COMMAND_WORD:
            return new UnmarkCommandParser().parse(args);
        default:
            throw new ParseException(UNKNOWN_COMMAND_MESSAGE);
        }
    }
}
