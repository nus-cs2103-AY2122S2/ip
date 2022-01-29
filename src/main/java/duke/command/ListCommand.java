package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;

/**
 * Represents a ListCommand which tells Duke to print out the list of current tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public void execute(List taskList, Ui ui, Storage storage) {
        ui.printList(taskList);
    }
}