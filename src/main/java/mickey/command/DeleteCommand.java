package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.Task;
import mickey.task.TaskList;

/**
 * Delete command to delete task.
 */
public class DeleteCommand extends Command {
    /**
     * Constructor.
     *
     * @param fullCommand User input command.
     */
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * Execute command.
     *
     * @param tasks List of tasks.
     * @param ui Ui to print feedback.
     * @param storage Storage to store tasks.
     * @return Response after executing command.
     * @throws MickeyException Exception for invalid commands.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        int indexToDelete = getIndex();
        if (indexToDelete >= tasks.size()) {
            throw new MickeyException("\tNo such task!");
        }
        Task removed = tasks.remove(indexToDelete);
        return ui.showDeleteTask(tasks.size(), removed);
    }
}
