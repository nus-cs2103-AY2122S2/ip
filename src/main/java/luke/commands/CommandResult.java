package luke.commands;

/**
 * Represents the result of executing a command.
 */
public class CommandResult implements Result {
    private String result;
    private boolean isExit;

    /**
     * Constructs a command result with the specified result.
     *
     * @param result The specified result for the command.
     */
    public CommandResult(String result) {
        this(result, false);
    }

    /**
     * Constructs a command result with the specified result and whether the command would result in exit.
     *
     * @param result The specified result for the command.
     * @param isExit Whether the command would result in an exit status.
     */
    CommandResult(String result, boolean isExit) {
        this.result = result;
        this.isExit = isExit;
    }

    public String getResult() {
        return this.result;
    }

    public boolean isExit() {
        return isExit;
    }

}
