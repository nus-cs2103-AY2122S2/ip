package duke.util;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.HashMap;
import java.util.StringTokenizer;

public class Parser {
    private final static String COMMAND_DONT_EXIST = "HEY! I don't know what this mean, command doesn't exist.";
    private final HashMap<String, Command> commands;

    public Parser(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    public Command parse(String fullInput) throws DukeException {
        StringTokenizer st = new StringTokenizer(fullInput, " ");
        String firstCommand = st.nextToken();

        if (commands.containsKey(firstCommand)) {
            return commands.get(firstCommand);
        } else {
            throw new DukeException(COMMAND_DONT_EXIST);
        }
    }
}
