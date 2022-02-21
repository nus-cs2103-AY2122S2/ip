package duke.command;

/**
 * Represents the command to be executed by duke.
 */
public abstract class Command {
    /**
     * Executes what the command specifies.
     */
    public abstract void execute();
    public abstract String getResponse();

}
