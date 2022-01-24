package duke.command.index;

import duke.command.Command;

/**
 * Represents a command that uses index to interact with the task list. An
 * <code>IndexCommand</code> object records the index of a task input
 * by the user. A <code>IndexCommand</code> object cannot be created
 * as it is an abstract base class for all index commands.
 */
public abstract class IndexCommand extends Command {
    protected static final String COMMAND_ARG = " <valid task no>";
    protected final int index;

    /**
     * Creates an instance of a IndexCommand object.
     *
     * @param index the index of the task in the task list.
     */
    public IndexCommand(int index) {
        this.index = index;
    }
}
