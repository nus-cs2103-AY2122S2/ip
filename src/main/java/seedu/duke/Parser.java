package seedu.duke;

import java.util.HashMap;

import seedu.commands.ByeCommand;
import seedu.commands.Command;
import seedu.commands.DeadlineCommand;
import seedu.commands.DeleteCommand;
import seedu.commands.EventCommand;
import seedu.commands.FindCommand;
import seedu.commands.ListCommand;
import seedu.commands.MarkCommand;
import seedu.commands.PriorityCommand;
import seedu.commands.TodoCommand;
import seedu.commands.UnmarkCommand;

/**
 * The Parser class
 */
public class Parser {

    private HashMap<String, Command> converter = new HashMap<>();

    /**
     * A dictionary converting string to their appropriate commands
     */
    public Parser() {
        converter.put("bye", new ByeCommand());
        converter.put("todo", new TodoCommand());
        converter.put("list", new ListCommand());
        converter.put("find", new FindCommand());
        converter.put("mark", new MarkCommand());
        converter.put("event", new EventCommand());
        converter.put("delete", new DeleteCommand());
        converter.put("unmark", new UnmarkCommand());
        converter.put("deadline", new DeadlineCommand());
        converter.put("priority", new PriorityCommand());
    }

    /**
     * Find and returns the appropriate command based on user input
     *
     * @param fullCmd The entire user input
     * @return A command object corresponding to the input command type
     * @throws DukeException There is no command or the command input has the wrong type
     */
    public Command parse(String fullCmd) throws DukeException {

        String[] cmds = fullCmd.trim().split(" ", 2);

        if (cmds.length == 0) {
            throw new DukeException("There is no command.");
        }

        if (!converter.containsKey(cmds[0].trim())) {
            throw new DukeException("Wrong command.");
        }

        Command cmd = converter.get(cmds[0].trim());

        if (cmds.length == 2) {
            cmd.validate(cmds[1].trim());
        } else {
            assert cmds.length == 1 : "Array not suppose to contain 2 items";
            cmd.validate("");
        }

        return cmd;
    }
}
