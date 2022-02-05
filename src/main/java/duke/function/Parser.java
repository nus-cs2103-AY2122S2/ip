package duke.function;

import java.util.StringTokenizer;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TagCommand;
import duke.exception.DukeException;

/**
 * Represents a parser to help manage user input.
 */
public class Parser {

    Parser() {
    }

    /**
     * Parses the user input into a command.
     * If the command is not a valid command, a DukeException is thrown.
     *
     * @param fullCommand Command input by the user.
     * @return Command instance.
     * @throws DukeException Custom exception containing the error message.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String firstToken = new StringTokenizer(fullCommand).nextToken();
        Command command = null;

        switch (firstToken) {
        case "list":
            command = new ListCommand(fullCommand);
            break;
        case "help":
            command = new HelpCommand(fullCommand);
            break;
        case "todo":
        case "deadline":
        case "event":
            command = new AddCommand(fullCommand);
            break;
        case "mark":
        case "unmark":
            command = new MarkCommand(fullCommand);
            break;
        case "delete":
            command = new DeleteCommand(fullCommand);
            break;
        case "find":
            command = new FindCommand(fullCommand);
            break;
        case "tag":
            command = new TagCommand(fullCommand);
            break;
        case "bye":
            command = new ExitCommand(fullCommand);
            break;
        default:
            command = new InvalidCommand(fullCommand);
            break;
        }

//        assert command != null;

        return command;

    }
}
