package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

public class InvalidCommand extends Command {

    /**
     * Returns the invalid command message when an invalid user input is provided.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = "";
        output = Ui.append(output, Messages.UNKNOWN_COMMAND);
        output = Ui.append(output, ui.showCommands());
        output += Messages.TRY_HELP_MSG;
        return output;
    }
}
