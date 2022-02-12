package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

public class HelpCommand extends Command {

    /**
     * Displays a message to explain the features of Duke.
     *
     * @param tasks TaskList that command is executed on.
     * @param ui User interface that interacts with the user.
     * @param storage Storage that saves and loads tasks after Command is executed.
     * @return
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = Messages.HELP_INTRO;
        output += ui.showCommands();
        output += Messages.HELP_BODY;
        return output;
    }
}
