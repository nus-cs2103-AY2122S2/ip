package bob.command;

import bob.Storage;
import bob.TaskList;
import bob.Ui;
import bob.exception.BobException;
import bob.task.Task;

public class PriorityCommand extends Command {
    private int index;
    private String priority;

    /**
     * {@inheritDoc}
     * Sets the task's priority to HIGH, MEDIUM, LOW or NONE.
     */
    public PriorityCommand(int index, String priority) {
        this.index = index;
        this.priority = priority;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage store) throws BobException {
        Task current = tasks.getTask(index);
        current.setTaskPriority(priority);
        StringBuilder reply = new StringBuilder("Bob: The following task's priority has been set to ")
                .append(priority.toUpperCase())
                .append("!\n")
                .append("\t" + current.printStatus());
        store.updateStore(tasks);
        return reply.toString();
    }
}
