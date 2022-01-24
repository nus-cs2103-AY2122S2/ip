package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class IncorrectCommand extends Command {
    private String incorrectMessage;
    public IncorrectCommand(String s) {
        incorrectMessage = s;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return incorrectMessage;
    }

    public boolean isExit() {
        return false;
    }
}
