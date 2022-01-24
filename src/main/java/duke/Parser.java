package duke;

import command.Command;
import command.ExitCommand;
import command.ListCommand;
import command.UpdateCommand;
import command.AddCommand;
import command.DeleteCommand;
import task.TaskList;

public class Parser {
    public Parser() {

    }

    public static Command parse(String command, TaskList tasks) throws DukeException {
        String[] tokenizedCommand = command.split(" ");
        new DukeException().validateInputs(command, tokenizedCommand, tasks);
        Command c = null;
        switch (tokenizedCommand[0]) {
        case "bye":
            c = new ExitCommand(command, tokenizedCommand);
            break;
        case "list":
            c = new ListCommand(command, tokenizedCommand);
            break;
        case "mark":
        case "unmark":
            c = new UpdateCommand(command, tokenizedCommand);
            break;
        case "todo":
        case "deadline":
        case "event":
            c = new AddCommand(command, tokenizedCommand);
            break;
        case "delete":
            c = new DeleteCommand(command, tokenizedCommand);
            break;
        default:
            break;
        }
        return c;
    }
}
