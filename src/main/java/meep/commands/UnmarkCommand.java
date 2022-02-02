package meep.commands;

import meep.task.ListTask;
import meep.task.Task;

/**
 * Marks a task as undone in task list.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_UNMARK = "OK, I've marked this task as not done yet:\n";

    private final int index;


    /**
     * Constructor for class UnMarkCommand
     *
     * @param index index of task to unmark
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }


    /**
     * Returns successfully unmarked message.
     *
     * @param tasks task list.
     * @return successfully unmarked message.
     */
    @Override
    public String execute(ListTask tasks) {
        Task task = tasks.get(index);
        task.unmarkDone();
        return MESSAGE_UNMARK + task.toString();
    }


    /**
     * Returns command word.
     *
     * @return command word.
     */
    @Override
    public String toString() {
        return COMMAND_WORD;
    }
}
