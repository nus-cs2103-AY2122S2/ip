package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.TaskList;
import com.duke.util.Ui;

public class ExitCommand extends Command {

    /**
     * Execute the command to exit the Duke program.
     * @param tasks List of Task
     * @param ui User interface
     * @param storage Storage used by Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks);
        ui.showMsg("\t Bye. Hope to see you again soon!");
    }

    /**
     * Whether to exit Duke.
     * @return boolean value to indicate whether to exit Duke
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
