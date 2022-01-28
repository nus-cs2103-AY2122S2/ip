package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    public ExitCommand(){

    }

    @Override
    public boolean exec(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
        ui.close();
        return false;
    }
}
