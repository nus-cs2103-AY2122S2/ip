package doge;

import doge.command.ByeCommand;
import doge.command.Command;
import doge.command.DeadlineCommand;
import doge.command.DeleteCommand;
import doge.command.EventCommand;
import doge.command.FindCommand;
import doge.command.ListCommand;
import doge.command.MarkCommand;
import doge.command.TodoCommand;
import doge.command.UnmarkCommand;

import doge.exception.DogeException;

import doge.task.Deadline;
import doge.task.Event;
import doge.task.Todo;

/**
 * Represents a parser that helps to parse the input information for Doge bot to understand.
 */
public class Parser {

    /**
     * Returns a Command that Doge bot understands in order for it to execute.
     *
     * @param input the user input given
     * @return Command that Doge bot can execute upon
     * @throws DogeException if user gives an invalid/unrecognisable command
     */
    public static Command parse(String input) throws DogeException {
        String curr = input.toLowerCase().trim();
        String c = curr.split(" ")[0].trim();

        switch (c) {
        case "todo":
            return new TodoCommand(new Todo(curr.substring(4).trim()));
        case "deadline":
            return new DeadlineCommand(new Deadline(curr.substring(8).trim()));
        case "event":
            return new EventCommand(new Event(curr.substring(5).trim()));
        case "mark":
            return new MarkCommand(curr.substring(4).trim());
        case "unmark":
            return new UnmarkCommand(curr.substring(6).trim());
        case "delete":
            return new DeleteCommand(curr.substring(6).trim());
        case "list":
            return new ListCommand(curr.substring(4).trim());
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindCommand(curr.substring(4).trim());
        default:
            throw new DogeException("Command not recognised!");
        }
    }
}
