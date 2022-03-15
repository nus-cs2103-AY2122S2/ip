package duke.commands.parser;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.commands.UpdateDeadlineCommand;
import duke.commands.UpdateEventCommand;
import duke.commands.UpdateNameCommand;
import duke.exceptions.DeadlineException;
import duke.exceptions.DukeException;
import duke.exceptions.EventException;
import duke.exceptions.ParseException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

import java.time.format.DateTimeParseException;

/**
 * Represents a <code>Parser</code> which parses input strings
 * to <code>Duke</code> and returns the appropriate Command.
 */


public class Parser {

    /**
     * Default constructor.
     */
    public Parser() {}

    /**
     * Parses input string to return appropriate enum type.
     * @param input
     * @return The appropriate enum type.
     */
    public Command parse(String input) throws DukeException {
        assert !input.isEmpty() : "Given input should not be empty";
        if (input.startsWith("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            return new TodoCommand(input);
        } else if (input.startsWith("mark")) {
            return new MarkCommand(input);
        } else if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        } else if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        } else if (input.startsWith("find")) {
            return new FindCommand(input);
        } else if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        } else if (input.startsWith("event")) {
            return new EventCommand(input);
        } else if (input.startsWith("update name")) {
            return new UpdateNameCommand(input);
        } else if (input.startsWith("update deadline")) {
            return new UpdateDeadlineCommand(input);
        } else if (input.startsWith("update event time")) {
            return new UpdateEventCommand(input);
        } else if (input.startsWith("bye")) {
            return new ByeCommand();
        } else {
            throw new ParseException("Invalid Command! See user guide for list of commands.");
        }
    }


}
