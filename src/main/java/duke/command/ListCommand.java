package duke.command;

import duke.List;
import duke.Storage;
import duke.Ui;
/**
 * Represents a ListCommand which tells duke.Duke to print out the list of current tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(List taskList, Ui ui, Storage storage) {
        return ui.showList(taskList);
    }
}