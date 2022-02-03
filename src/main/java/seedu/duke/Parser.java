package seedu.duke;

import seedu.commands.*;

import java.util.HashMap;

public class Parser {

    private final HashMap<String, Command> converter = new HashMap<>();

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
