package duke.commands;

import duke.Storage;
import duke.TaskManager;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand(){
        super();
    }

    @Override
    public boolean isExit(){
        return true;
    }

    public boolean execute(Storage storage, Ui ui, TaskManager taskManager){
        ui.showBye();
        return true;
    }
}
