package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.Task;
import com.duke.task.TaskList;

public class DeleteCommand extends Command {

    private int pos;

    public DeleteCommand(int pos) {
        this.pos = pos;
    }

    /**
     * Execute the command to delete task in the list.
     * @param tasks List of Task
     * @param storage Storage used by Duke
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        Task ts = tasks.remove(pos-1);
        return "Noted, I've removed this task: \n " + ts + "\n Now you have "
                + tasks.getCount() + " tasks in the list.";
    }
}
