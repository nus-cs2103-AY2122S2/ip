package seedu.commands;

import seedu.duke.Ui;
import seedu.storage.Storage;
import seedu.storage.TaskList;

public class ByeCommand extends Command {

    @Override
    public void input(String input) {
        //not in use
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        super.isExit = true;
    }
}
