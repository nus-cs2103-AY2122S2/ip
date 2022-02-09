package bobby.command;


import bobby.Storage;
import bobby.Ui;
import bobby.exception.BobbyException;
import bobby.exception.InvalidNumberException;
import bobby.exception.MarkException;
import bobby.task.Task;
import bobby.task.TaskList;

/**
 * Represents an 'unmark' command.
 */
public class UnmarkCommand extends Command {
    /** The task index to be marked */
    private String toUnmark;

    /**
     * Creates an UnmarkCommand object.
     *
     * @param toUnmark The task index to be unmarked.
     */
    public UnmarkCommand(String toUnmark) {
        this.toUnmark = toUnmark;
    }

    /**
     * {@inheritDoc}
     *
     * @param tasks TaskList object containing a list of Tasks.
     * @param ui Ui object to allow for Bobby to print messages.
     * @param storage Storage object that handles the reading/writing of TaskList into a specified file.
     * @return Bobby's reply to the command.
     * @throws BobbyException if an invalid command is given by the user's input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BobbyException {
        int indexUnmark;
        try {
            indexUnmark = Integer.parseInt(toUnmark) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("letter");
        }
        Task task = tasks.getIndex(indexUnmark);
        if (!task.isDone()) {
            throw new MarkException("alr_unmarked");
        }
        task.unmarkDone();
        storage.saveTasks(tasks.getTaskList());
        return ui.unmarkMessage(task);
    }

    /**
     * Overrides the default equals() method. Compares if 2 objects are of the same Command type.
     *
     * @param obj The other Command object to compare with.
     * @return True if both are UnmarkCommand objects. False otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnmarkCommand;
    }
}
