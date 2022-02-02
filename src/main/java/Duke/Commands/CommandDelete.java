package Duke.Commands;

import Duke.DukeException.DukeException;
import Duke.System.Storage;
import Duke.System.TaskList;
import Duke.System.Ui;

public class CommandDelete extends Command {
    private final int taskNum;

    public CommandDelete(String str) throws DukeException {
        try {
            this.taskNum = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid input!");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.delete(this.taskNum);
        storage.save(tasks);
        ui.showTaskDeleted();
    }
}
