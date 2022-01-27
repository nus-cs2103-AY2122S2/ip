package mickey.command;

import mickey.app.MickeyException;
import mickey.app.Storage;
import mickey.app.Ui;
import mickey.task.Task;
import mickey.task.TaskList;

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
