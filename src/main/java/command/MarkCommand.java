package command;

import app.MickeyException;
import app.Storage;
import app.Ui;
import task.Task;
import task.TaskList;

public class MarkCommand extends Command {
    public MarkCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MickeyException {
        int indexToMark = getIndex();
        if (indexToMark >= tasks.size()) {
            throw new MickeyException("No such task!");
        }
        Task toMark;
        if (cmd.equals("mark")) {
            toMark = tasks.get(indexToMark).markAsDone();
            ui.showMarkAsDone(toMark);
        } else {
            toMark = tasks.get(indexToMark).markAsUndone();
            ui.showMarkAsUndone(toMark);
        }
    }
}
