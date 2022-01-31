package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.command.Command;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTasks(tasks);
    }

}