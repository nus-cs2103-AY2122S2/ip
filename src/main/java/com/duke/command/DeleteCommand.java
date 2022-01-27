package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.Ui;

public class DeleteCommand extends Command {

    private int pos;

    public DeleteCommand(int pos) {
        this.pos = pos;
    }

    /**
     * Execute the command to delete task in the list.
     * @param tasks List of Task
     * @param ui User interface
     * @param storage Storage used by Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task ts = tasks.remove(pos-1);
        ui.showMsg("\t Noted, I've removed this task:");
        ui.showMsg("\t  " + ts);
        System.out.println("\t Now you have " + tasks.getCount() + " tasks in the list.");
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
