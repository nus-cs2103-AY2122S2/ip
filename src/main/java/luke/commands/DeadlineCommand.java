package luke.commands;

import luke.data.tasks.Deadline;

/**
 * Implements the deadline command.
 */
public class DeadlineCommand extends AddCommand {

    /**
     * Constructs the deadline command with the specified deadline task.
     * @param deadline The specified deadline task to be added.
     */
    public DeadlineCommand(Deadline deadline) {
        super(deadline);
    }
}
