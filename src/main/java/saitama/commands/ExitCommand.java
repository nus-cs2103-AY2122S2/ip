package saitama.commands;

import saitama.Storage;
import saitama.TaskList;
import saitama.Ui;

/**
 * A Command object to exit the chatbot.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showSave();
        storage.save(taskList.toArrayList());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
