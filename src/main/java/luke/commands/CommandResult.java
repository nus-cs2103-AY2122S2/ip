package luke.commands;

/**
 * Represents the result of executing a command.
 */
public class CommandResult {
    private String result;

    /**
     * Constructs a command result with the specified result.
     *
     * @param result The specified result for the command.
     */
    CommandResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }
}
