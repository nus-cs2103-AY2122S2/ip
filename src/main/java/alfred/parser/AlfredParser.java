package alfred.parser;

import alfred.command.Command;
import alfred.command.DeadlineCommand;
import alfred.command.DeleteCommand;
import alfred.command.EventCommand;
import alfred.command.ExitCommand;
import alfred.command.FindCommand;
import alfred.command.ListCommand;
import alfred.command.MarkCommand;
import alfred.command.ToDoCommand;
import alfred.command.UnmarkCommand;
import alfred.exceptions.IllegalCharacterException;
import alfred.exceptions.InvalidCommandException;
import alfred.task.Task;

/**
 * Encapsulates a parser used to identify the command
 * being passed to the Alfred bot.
 */
public class AlfredParser {

    /**
     * Parses the input from the console.
     *
     * @param input String input from the console.
     * @return Command object that can be executed.
     * @throws InvalidCommandException if no valid command is identified in the input.
     */
    public Command parseInput(String input) throws InvalidCommandException,
            IllegalCharacterException {

        assert input != null;

        // read in arguments
        String[] arguments = input.split(" "); // expected split in console input
        String command = arguments[0]; // expect first word as command

        // case by case, check for valid input
        if (input.contains(Task.FORMAT_SPLIT)) {
            throw new IllegalCharacterException();
        }
        if ((command.equals("list")) && (arguments.length == 1)) { // exactly one word expected
            return new ListCommand();
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
