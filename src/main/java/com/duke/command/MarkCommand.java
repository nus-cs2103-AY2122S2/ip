package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.TaskList;
import com.duke.util.Ui;

/**
 * Command to mark the todo item as done.
 */
public class MarkCommand extends Command {

    private int pos;

    public MarkCommand(int pos) {
        this.pos = pos;
    }

    /**
     * Execute the command to mark the Task as done
     * @param tasks List of Task
     * @param ui User interface
     * @param storage Storage used by Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(pos-1).markAsDone();
        ui.showMsg("\t Nice! I've marked this task as done:\n" + "\t  " + tasks.get(pos-1));
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
