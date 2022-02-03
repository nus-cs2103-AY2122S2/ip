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
import seedu.commands.TodoCommand;
import seedu.commands.UnmarkCommand;

public class Parser {

    private final HashMap<String, Command> converter = new HashMap<>();

    /**
     * Links string command to command objects.
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
    }

    /**
     * Parses the given string and checks whether the command exists for the given string.
     * @param fullCmd input string from command line
     * @return An initialised command object specified by the string
     * @throws DukeException Throws any error found.
     */
    public Command parse(String fullCmd) throws DukeException {

        String[] cmds = fullCmd.trim().split(" ", 2);

        if (cmds.length == 0) {
            throw new DukeException("There is no command.");
        } else {
            if (converter.containsKey(cmds[0].trim())) {
                Command cmd = converter.get(cmds[0].trim());

                if (cmds.length == 2) {
                    cmd.input(cmds[1].trim());
                } else {
                    cmd.input("");
                }

                return cmd;
            } else {
                throw new DukeException("Wrong command.");
            }
        }
    }
}
