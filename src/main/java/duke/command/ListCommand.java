package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

public class ListCommand extends Command {

    /**
     * Prints out the TaskList.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        output = Ui.append(output, Messages.LIST_MSG);
        for (int i = 0; i < tasks.getSize(); i++) {
            output = Ui.append(output, i + 1 + ". " + tasks.getTaskStatement(i));
        }
        return output;
    }
}
