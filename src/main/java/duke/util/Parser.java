package duke.util;

import java.util.HashMap;
import java.util.StringTokenizer;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Deals with making sense of the user command.
 */
public class Parser {
    private static final String COMMAND_DONT_EXIST = "HEY! I don't know what this mean, command doesn't exist.";
    private final HashMap<String, Command> commands;

    /**
     * Parser constructor.
     *
     * @param commands Hashmap, takes in String, return command. Command call keyword is the key.
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
