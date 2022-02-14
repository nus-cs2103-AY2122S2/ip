package luke.commands;

import luke.data.tasks.RecurringTask;

/**
 * Implements the recur command.
 */
public class RecurCommand extends AddCommand {

    /**
     * Constructs the recur command with the specified recurring task.
     *
     * @param recurringTask The specified recurring task to be added.
     */
    public RecurCommand(RecurringTask recurringTask) {
        super(recurringTask);
    }
}
