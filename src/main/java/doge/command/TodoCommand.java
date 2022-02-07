package doge.command;

import doge.Storage;
import doge.TaskList;
import doge.Ui;
import doge.exception.DogeException;
import doge.exception.TodoException;
import doge.task.Task;

/**
 * Represents the "todo" command. Doge bot will add a task of "todo" type into the TaskList.
 */
public class TodoCommand extends Command {

    /**
     * Constructor for class TodoCommand.
     *
     * @param task the todo task that is associated with this command
     */
    public TodoCommand (Task task) {
        super(task);
    }

    /**
     * Executes the "todo" command. It adds a task of "todo" type into the TaskList.
     *
     * @param tasks {@inheritDoc}
     * @param ui {@inheritDoc}
     * @param storage {@inheritDoc}
     * @throws DogeException if it fails to add the "todo" task into the TaskList
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DogeException {
        assert tasks != null : "TaskList should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        if (this.task.getDescription().isEmpty()) {
            throw new TodoException("So doing NOTHING is a task? doge.task.Task details cannot be left empty!");
        } else {
            tasks.addTask(this.task);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Stop troubling me, I've already added this task:";
    }

}
