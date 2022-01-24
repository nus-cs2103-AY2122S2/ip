package duke.function;

import java.util.StringTokenizer;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.exception.DukeException;

public class Parser {

    Parser() {
    }

    public static Command parse(String fullCommand) throws DukeException {
        StringTokenizer st = new StringTokenizer(fullCommand);
        Command command;

        switch (st.nextToken()) {
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
        case "bye":
            command = new ExitCommand(fullCommand);
            break;
        }

        throw new DukeException("That is not a valid command\nPlease type 'help' to see a list of valid commands");
    }
}
