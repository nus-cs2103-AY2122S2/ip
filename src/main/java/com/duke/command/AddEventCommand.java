package com.duke.command;

import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.Storage;
import com.duke.util.Ui;

public class AddEventCommand extends Command {

    private Task task;

    public AddEventCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the command to add event task into the list.
     * @param tasks List of Task
     * @param ui User interface
     * @param storage Storage used by Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMsg("\t Got it, I've added this task:");
        ui.showMsg("\t  " + tasks.get(tasks.getCount()-1));
        ui.showMsg("\t Now you have " + tasks.getCount() + " tasks in the list.");
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
