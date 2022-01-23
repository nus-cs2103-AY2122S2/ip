package Duke.Commands;

import Duke.Managers.TaskList;
import Duke.Managers.Ui;
import Duke.Managers.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui io, Storage storage) {
        io.showMessage("Here are the tasks in your list:");
        int count = 0;
        for (int i = 0; i < tasks.getSize(); i++) {
            io.showMessage(i + 1 + ". " + tasks.get(i).toString());
        }
    }
}
