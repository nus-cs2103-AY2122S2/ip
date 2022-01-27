package duke.util;

import duke.command.Command;
import duke.exception.DukeException;

import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private final HashMap<String, Command> commands;

    private final static String COMMAND_DONT_EXIST = "HEY! I don't know what this mean, command doesn't exist.";

    /**
     * Parser constructor.
     *
     * @param commands Hash map <String, command> of commands with their command call keyword as the key.
     */
    public Parser(HashMap<String, Command> commands) {
        this.commands = commands;
    }

    /**
     * Returns the Command based on user input.
     *
     * @param fullInput User input.
     * @return Returns the command based on user input.
     * @throws DukeException If command keyword does not exist.
     */
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
