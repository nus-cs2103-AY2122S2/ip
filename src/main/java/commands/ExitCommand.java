package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.respond("Bye. Hope to see you again soon!");
    }

    public boolean isExit() {
        return true;
    }
}
