package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class WelcomeCommand extends Command {
    /**
     * Say welcome to the user.
     * @param tasks The list containing all the tasks
     * @param ui User interface
     * @param storage Class that manages storage
     **/
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Hello! I am xpz\nWhat can I do for you?\n";
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof WelcomeCommand;
    }
}
