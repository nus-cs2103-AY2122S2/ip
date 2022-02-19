package stevie.parser;

import stevie.command.Command;
import stevie.command.ExitCommand;
import stevie.command.HelpCommand;
import stevie.command.ListCommand;
import stevie.command.UndoCommand;
import stevie.exception.ParserException;
import stevie.exception.TaskException;
import stevie.exception.messages.ParserExceptionMessages;
import stevie.parser.types.add.DeadlineTaskParser;
import stevie.parser.types.add.EventTaskParser;
import stevie.parser.types.add.TodoTaskParser;
import stevie.parser.types.edit.DeleteParser;
import stevie.parser.types.edit.MarkParser;
import stevie.parser.types.find.FindParser;

/**
 * StevieParser parses user's input to creates a Command. Command is used to alter the user's task list.
 */
public class StevieParser {
    /**
     * Parses a user's input to produce a command. A valid command is only produced if user's
     * input is complete. If not, an exception is thrown to inform user of missing information.
     *
     * @param userIn user's input
     * @return a command if a valid input is given
     * @throws ParserException if user input does not correspond to any command
     * @throws TaskException if creation of command to add task fails to construct a task
     */
    public static Command parse(String userIn) throws ParserException, TaskException {
        String[] splitInput = userIn.split(" ", 2);
        if (splitInput.length == 1) {
            String command = splitInput[0];
            return parseSimpleCommand(command);
        } else {
            String command = splitInput[0];
            String commandParams = splitInput[1];
            return parseComplexCommand(command, commandParams);
        }
    }

    /**
     * Parse a string input into a command.
     * @param command command keyword
     * @return a command that corresponds to user's input
     * @throws ParserException if user input does not correspond to any command
     */
    public static Command parseSimpleCommand(String command) throws ParserException {
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand();
        case "undo":
            return new UndoCommand();
        default:
            throw new ParserException(ParserExceptionMessages.InvalidCommandParseError);
        }
    }

    /**
     * Parse a string input, and additional parameters into a command.
     * @param command command keyword
     * @param commandParams additional parameters for the command
     * @return a command that corresponds to user's input
     * @throws ParserException if user input does not correspond to any command
     * @throws TaskException if creation of command to add task fails to construct a task
     */
    public static Command parseComplexCommand(String command, String commandParams)
            throws ParserException, TaskException {
        switch (command) {
        case "mark":
            return new MarkParser(commandParams, true).parse();
        case "unmark":
            return new MarkParser(commandParams, false).parse();
        case "find":
            return new FindParser(commandParams).parse();
        case "delete":
            return new DeleteParser(commandParams).parse();
        case "todo":
            return new TodoTaskParser(commandParams).parse();
        case "deadline":
            return new DeadlineTaskParser(commandParams).parse();
        case "event":
            return new EventTaskParser(commandParams).parse();
        default:
            throw new ParserException(ParserExceptionMessages.InvalidCommandParseError);
        }
    }
}
