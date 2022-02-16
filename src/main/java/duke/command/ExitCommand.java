package duke.command;

import duke.operations.TaskList;
import duke.operations.Ui;

/**
 * Represents a subclass of Command.
 */
public class ExitCommand extends Command {
    /**
     * A constructor for <code>ExitCommand</code>.
     */
    public ExitCommand() {
        super(null, null, null);
    }

    /**
     * Exits Duke.
     *
     * @param tasks the task to be exited.
     * @return the task to be printed out by GUI.
     */
    @Override
    public String execute(TaskList tasks) {
        return Ui.showGoodbye();
    }
}
