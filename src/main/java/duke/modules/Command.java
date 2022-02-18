package duke.modules;

/**
 * Represents a chatbot command.
 */
public abstract class Command {

    /**
     * <p> Executes the command and its relevant tasks before returning a String output to indicate status of command
     * execution. </p>
     *
     * @return A string indicating status of execution.
     */
    public abstract String execute();
}
