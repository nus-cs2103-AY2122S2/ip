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

        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new InfoCommand();
        } else if (command.equals("mark")) {
            int index = Integer.parseInt(arguments);
            return new UpdateCommand(true, index);
        } else if (command.equals("unmark")) {
            int index = Integer.parseInt(arguments);
            return new UpdateCommand(false, index);
        } else if (command.equals("todo")) {
            return new AddCommand(arguments);
        } else if (command.equals("deadline")) {
            String[] argsArray = arguments.split(" /by ");
            return new AddCommand(argsArray[0], argsArray[1], "DEADLINE");
        } else if (command.equals("event")) {
            String[] argsArray = arguments.split(" /at ");
            return new AddCommand(argsArray[0], argsArray[1], "EVENT");
        } else if (command.equals("find")) {
            return new FindCommand(arguments);
        } else if (command.equals("delete")) {
            return new DeleteCommand(Integer.parseInt(arguments));
        }
        throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
