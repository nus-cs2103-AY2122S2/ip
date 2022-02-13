package seedu.duke;

import java.util.HashMap;

import seedu.commands.*;

/**
 * Parses the input of the user
 */
public class Parser {

    private final HashMap<String, Command> CONVERTER = new HashMap<>();

    /**
     * Links string command to command objects.
     */
    public Parser() {
        CONVERTER.put("bye", new ByeCommand());
        CONVERTER.put("todo", new TodoCommand());
        CONVERTER.put("list", new ListCommand());
        CONVERTER.put("find", new FindCommand());
        CONVERTER.put("mark", new MarkCommand());
        CONVERTER.put("event", new EventCommand());
        CONVERTER.put("delete", new DeleteCommand());
        CONVERTER.put("unmark", new UnmarkCommand());
        CONVERTER.put("deadline", new DeadlineCommand());
        CONVERTER.put("priority", new PriorityCommand());
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
        }

        if (!CONVERTER.containsKey(cmds[0].trim())) {
            throw new DukeException("Wrong command.");
        }

        Command cmd = CONVERTER.get(cmds[0].trim());

        if (cmds.length == 2) {
            cmd.validate(cmds[1].trim());
        } else {
            assert cmds.length == 1: "Array not suppose to contain 2 items";
            cmd.validate("");
        }

        return cmd;
    }
}
