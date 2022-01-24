package duke.function;

import java.util.StringTokenizer;

import duke.exception.DukeException;
import duke.command.Command;
import duke.command.ListCommand;
import duke.command.HelpCommand;
import duke.command.AddCommand;
import duke.command.MarkCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;

public class Parser {

    Parser() {
    }

    public static Command parse(String fullCommand) throws DukeException {
        StringTokenizer st = new StringTokenizer(fullCommand);
        switch (st.nextToken()) {
        case "list":
            return new ListCommand(fullCommand);
        case "help":
            return new HelpCommand(fullCommand);
        case "todo":
        case "deadline":
        case "event":
            return new AddCommand(fullCommand);
        case "mark":
        case "unmark":
            return new MarkCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "bye":
            return new ExitCommand(fullCommand);
        }

        throw new DukeException("That is not a valid command\nPlease type 'help' to see a list of valid commands");
    }
}
