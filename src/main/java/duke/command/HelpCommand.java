package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Messages;
import duke.ui.Ui;

public class HelpCommand extends Command {

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = Messages.HELP_INTRO;
        output += ui.showCommands();
        output += Messages.HELP_BODY;
        return output;
    }
}
