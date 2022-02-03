package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.TaskList;

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
     * @param storage Storage used by Duke
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.get(pos-1).markAsDone();
        return "Nice! I've marked this task as done:\n" + "\t  " + tasks.get(pos-1);
    }
}
