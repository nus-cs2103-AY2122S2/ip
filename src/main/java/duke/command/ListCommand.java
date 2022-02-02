package duke.command;

import duke.Storage;
import duke.Ui;
import duke.TaskList;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.list();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
