package apollo.commands;

import apollo.exceptions.ApolloOutOfBoundsException;
import apollo.tasks.Task;

/**
 * Marks {@code Task} as done or not done in taskList.
 * Extends {@code Command} superclass.
 */
public class MarkCommand extends Command {

    public static final String HELP_MARK_COMMAND = "Mark or unmark tasks at 1-base INDEX: mark|unmark <INDEX>";
    private final boolean isDone;
    private final int index;

    /**
     * Constructor for {@code MarkCommand}.
     *
     * @param index Of task to be marked.
     * @param isDone Boolean to mark task.
     */
    public MarkCommand(int index, boolean isDone) {
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * Marks specific task in taskList as done or not done.
     *
     * @return Message for successful execution.
     * @throws ApolloOutOfBoundsException If index supplied is out of bounds.
     */
    @Override
    public String execute() throws ApolloOutOfBoundsException {
        if (index < 0 || index >= taskList.taskCount()) {
            throw new ApolloOutOfBoundsException();
        }

        Task task = taskList.markTask(index, isDone);
        String doneStatus = isDone ? "done" : "not done";
        return String.format("I have marked the following task as %s\n\t%s",
                doneStatus, task);
    }
}
