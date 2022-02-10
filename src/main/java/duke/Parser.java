package duke;

import command.AddCommand;
import command.Command;
import command.DeleteCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.UpdateCommand;
import task.TaskList;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {
    public Parser() {

    }

    /**
     * Returns the user command categorized by the type of command.
     * @param command The user command that was input.
     * @param tasks The TaskList containing the list of tasks saved.
     * @return A type of Command based on the user input command.
     * @throws DukeException If there are invalid inputs.
     */
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
        case "find":
            c = new FindCommand(command, tokenizedCommand);
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
