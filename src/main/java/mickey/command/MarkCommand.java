package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.Task;
import mickey.task.TaskList;

/**
 * Mark command to mark task as done or undone.
 */
public class MarkCommand extends Command {
    /**
     * Constructor.
     *
     * @param fullCommand User input command.
     */
    public MarkCommand(String fullCommand) {
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
        int indexToMark = getIndex();
        if (indexToMark >= tasks.size()) {
            throw new MickeyException("No such task!");
        }
        Task toMark;
        if (cmd.equals("mark")) {
            toMark = tasks.get(indexToMark).markAsDone();
            return ui.showMarkAsDone(toMark);
        } else {
            toMark = tasks.get(indexToMark).markAsUndone();
            return ui.showMarkAsUndone(toMark);
        }
    }
}
