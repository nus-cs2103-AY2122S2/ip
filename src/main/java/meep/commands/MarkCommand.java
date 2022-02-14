package meep.commands;


import meep.task.ListTask;
import meep.task.Task;

/**
 * Marks a task as done in task list.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_MARK = "Nice! I've marked this task as done:\n";
    public static final int COMMAND_LENGTH = 2;

    private final int index;


    /**
     * Constructor for class MarkCommand.
     *
     * @param index index of task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Returns successfully marked message.
     *
     * @param tasks task list.
     * @return successfully marked message.
     */
    @Override
    public String execute(ListTask tasks) {
        Task task = tasks.get(index);
        task.markDone();
        return MESSAGE_MARK + task.toString();
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
