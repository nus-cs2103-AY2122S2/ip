package duke.command;

import duke.helptool.Storage;
import duke.helptool.TaskList;
import duke.helptool.Ui;

/**
 * The type List command.
 */
public class ListCommand extends Command {

    /**
     * Instantiates a new List command.
     */
    public ListCommand(){

    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }
}
