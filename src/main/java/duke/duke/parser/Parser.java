package duke.parser;

import duke.info.exception.InvalidCommandException;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.TodoCommand;
import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;

public class Parser {

    Parser() {}

    public static Command parse(String fullCommand) throws InvalidCommandException {
        String[] splitCommand = fullCommand.split(" ", 2); // splits the first word from the command
        String command = splitCommand[0];
        String input = "";
        if (splitCommand.length > 1) {
            input = splitCommand[1];
            input = input.trim();
        }

        switch(command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new ListCommand();
            case "todo":
                return new TodoCommand(input);
            case "deadline":
                String[] splitDeadline = input.split(" /by ", 2); // splits the action from the date
                return new DeadlineCommand(splitDeadline[0], splitDeadline[1]);
            case "event":
                String[] splitEvent = input.split(" /at ", 2);
                return new EventCommand(splitEvent[0], splitEvent[1]);
            case "mark":
                return new MarkCommand(input);
            case "unmark":
                return new UnmarkCommand(input);
            case "delete":
                return new DeleteCommand(input);
            case "find":
                return new FindCommand(input);
            default:
                throw new InvalidCommandException();
        }
    }
}
