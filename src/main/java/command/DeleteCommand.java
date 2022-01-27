package command;

import app.MickeyException;
import app.Storage;
import app.Ui;
import task.Task;
import task.TaskList;

public class DeleteCommand extends Command {
    public DeleteCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        int indexToDelete = getIndex();
        if (indexToDelete >= tasks.size()) {
            throw new MickeyException("\tNo such task!");
        }
        Task removed = tasks.remove(indexToDelete);
        ui.showDeleteTask(tasks.size(), removed);
    }
}
