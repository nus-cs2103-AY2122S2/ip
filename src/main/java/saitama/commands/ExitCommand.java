package saitama.commands;

import saitama.storage.Storage;
import saitama.tasks.TaskList;
import saitama.ui.Ui;

/**
 * A Command object to exit the chatbot.
 */
public class ExitCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.save(taskList.toArrayList());
        return ui.showSave();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
