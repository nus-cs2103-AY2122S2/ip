package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class ListCommand extends Command {

    public ListCommand() {
        super(Keyword.LIST);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showList(tasks);
    }

}
