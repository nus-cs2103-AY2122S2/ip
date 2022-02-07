package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand implements Command {
    protected String[] commandAndDetails;
    public FindCommand(String[] commandAndDetails) {
        this.commandAndDetails = commandAndDetails;
    }

    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listTasks(this.commandAndDetails[1]);
        return false;
    }
}
