package stevie.command;

/**
 * ChangeCommand is an abstract class that encapsulate commands that will
 * make changes to the task list.
 * @param <T> type of the parameter
 */
public abstract class ChangeCommand<T> extends Command {
    /**
     * Parameter of the command.
     */
    protected final T parameter;

    /**
     * Constructor for Change Command.
     * @param param parameter for command to execute
     */
    public ChangeCommand(T param) {
        parameter = param;
    }
}
