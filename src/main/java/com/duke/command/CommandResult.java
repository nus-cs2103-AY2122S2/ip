package com.duke.command;

/**
 * Represents a result of a Command execution.
 * Acts as a wrapper around a string describing the command execution result.
 */
public class CommandResult {
    private boolean isShutdown = false;
    private String resultMessage;

    /**
     * Constructor for this class.
     *
     * @param resultMessage A string describing the command execution result.
     */
    public CommandResult(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public boolean isShutdown() {
        return isShutdown;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Static method for creating a CommandResult intended to shutdown the bot.
     *
     * @return A CommandResult with a pre-defined shutdown message.
     */
    public static CommandResult shutdownResult() {
        CommandResult result = new CommandResult("LUMU: Goodbye. Hope to see you again soon");
        result.isShutdown = true;
        return result;
    }

    /**
     * Static method for creating a CommandResult for unknown arguments.
     *
     * @return A CommandResult with a pre-defined unknown argument message.
     */
    public static CommandResult unknownResult() {
        CommandResult result = new CommandResult(
                "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CommandResult) {
            CommandResult cmdresult = (CommandResult) obj;
            return (cmdresult.isShutdown == this.isShutdown
                    && cmdresult.resultMessage.equals(this.resultMessage));
        }
        return false;
    }
}
