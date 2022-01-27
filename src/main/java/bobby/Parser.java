package bobby;

import bobby.command.*;

public class Parser {

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
