package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.InfoCommand;
import duke.commands.UpdateCommand;
import duke.data.TaskList;
import duke.data.exception.InvalidCommandException;

/**
 * Makes sense of the user command.
 */
public class Parser {
    private TaskList taskList;
    public Parser() {
        this.taskList = new TaskList();
    }

    /**
     * Parses user input as a command for execution.
     *
     * @param scannedInput full user input string.
     * @return resultant command based on the user input.
     * @throws InvalidCommandException if an invalid command is provided.
     */
    public Command parse(String scannedInput) throws InvalidCommandException {
        String[] input = scannedInput.split(" ", 2);
        String command = input[0];
        String arguments = input.length > 1 ? input[1] : "";

        switch (command) {
            case "bye":
                return new ExitCommand();
            case "list":
                return new InfoCommand();
            case "mark":
                int taskToMark = Integer.parseInt(arguments);
                return new UpdateCommand(true, taskToMark);
            case "unmark":
                int taskToUnmark = Integer.parseInt(arguments);
                return new UpdateCommand(false, taskToUnmark);
            case "todo":
                return new AddCommand(arguments);
            case "deadline":
                String[] deadlineArgsArray = arguments.split(" /by ");
                return new AddCommand(deadlineArgsArray[0], deadlineArgsArray[1], "DEADLINE");
            case "event":
                String[] eventArgsArray = arguments.split(" /at ");
                return new AddCommand(eventArgsArray[0], eventArgsArray[1], "EVENT");
            case "find":
                return new FindCommand(arguments);
            case "delete":
                return new DeleteCommand(Integer.parseInt(arguments));
            default:
                throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
