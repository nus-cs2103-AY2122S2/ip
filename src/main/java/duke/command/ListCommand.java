package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    public ListCommand() {}

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String listToPrint = taskList.getList();
        ui.printOutput(listToPrint);
    }

    public boolean isExit() {
        return false;
    }
}
