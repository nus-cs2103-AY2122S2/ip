package Alfred.Parser;

import Alfred.Command.Command;
import Alfred.Command.DeadlineCommand;
import Alfred.Command.DeleteCommand;
import Alfred.Command.EventCommand;
import Alfred.Command.ExitCommand;
import Alfred.Command.FindCommand;
import Alfred.Command.ListCommand;
import Alfred.Command.MarkCommand;
import Alfred.Command.ToDoCommand;
import Alfred.Command.UnmarkCommand;
import Alfred.Exceptions.InvalidCommandException;

public class AlfredParser {

    public Command parseInput(String input) throws InvalidCommandException {
        // read in arguments
        String[] arguments = input.split(" ");
        String command = arguments[0];

        // case by case, check for valid input
        // LIST
        if ((command.equals("list")) && (arguments.length == 1)) {
            return new ListCommand();

            // (UN)MARK and DELETE
        } else if (command.equals("mark")) {
            return new MarkCommand(input);
        } else if (command.equals("unmark")) {
            return new UnmarkCommand(input);
        } else if (command.equals("delete")) {
            return new DeleteCommand(input);
        } else if (command.equals("todo")) {
            return new ToDoCommand(input);
        } else if (command.equals("event")) {
            return new EventCommand(input);
        } else if (command.equals("deadline")) {
            return new DeadlineCommand(input);
        } else if (command.equals("find")) {
            return new FindCommand(input);
        } else if (command.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new InvalidCommandException();
        }
    }
}
