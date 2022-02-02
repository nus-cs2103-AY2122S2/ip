package bobby;

import bobby.command.ByeCommand;
import bobby.command.Command;
import bobby.command.DeadlineCommand;
import bobby.command.DeleteCommand;
import bobby.command.EventCommand;
import bobby.command.FindCommand;
import bobby.command.InvalidCommand;
import bobby.command.ListCommand;
import bobby.command.MarkCommand;
import bobby.command.ToDoCommand;
import bobby.command.UnmarkCommand;

public class Parser {
    /**
     * Parses the command to allow Bobby to understand input.
     * @param fullCommand The command the user inputs.
     * @return The right Command object for execution.
     */
    public static Command parse(String fullCommand) {
        String[] fullCommandArr = fullCommand.split(" ");
        Command command;
        switch(fullCommandArr[0].toLowerCase()) {
        case "bye": command = new ByeCommand();
            break;
        case "list": command = new ListCommand();
            break;
        case "mark": command = new MarkCommand(fullCommand, fullCommandArr);
            break;
        case "unmark": command = new UnmarkCommand(fullCommand, fullCommandArr);
            break;
        case "todo": command = new ToDoCommand(fullCommand, fullCommandArr);
            break;
        case "deadline": command = new DeadlineCommand(fullCommand);
            break;
        case "event": command = new EventCommand(fullCommand);
            break;
        case "delete": command = new DeleteCommand(fullCommand, fullCommandArr);
            break;
        case "find": command = new FindCommand(fullCommand);
            break;
        default: command = new InvalidCommand();
        }
        return command;
    }
}
