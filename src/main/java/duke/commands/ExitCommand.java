package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Goodbye! Till the next time we meet!";
    }

    public boolean isExit() {
        return true;
    }
}
