package duke.commands;

import duke.tasks.TaskList;
import duke.managers.Ui;
import duke.managers.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui io, Storage storage) {
        io.showMessage("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            io.showMessage(i + 1 + ". " + tasks.get(i).toString());
        }
    }
}
