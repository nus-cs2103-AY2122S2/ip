package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.TaskList;
import com.duke.util.Ui;

public class ListCommand extends Command {

    /**
     * Execute the command to show the list of task
     * @param tasks List of Task
     * @param ui User interface
     * @param storage Storage used by Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMsg("\t Here are the tasks in your list:");
        for (int i=0; i<tasks.getCount(); i++) {
            ui.showMsg("\t " + (i+1) + "." + tasks.get(i));
        }
    }

    /**
     * Whether to exit Duke.
     * @return boolean value to indicate whether to exit Duke
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
