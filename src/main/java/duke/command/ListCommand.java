package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(List taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}