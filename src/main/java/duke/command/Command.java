package duke.command;

import java.util.Map;

/**
 * Encapsulates a CommandType and the parameters associated with it.
 */
public class Command {
    private final CommandType type;
    private final Map<String, String> params;

    /**
     * Constructs a Command instance.
     *
     * @param type   Type of command.
     * @param params Map containing the parameters associated with the command.
     */
    public Command(CommandType type, Map<String, String> params) {
        this.type = type;
        this.params = params;
    }

    public CommandType getType() {
        return type;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
