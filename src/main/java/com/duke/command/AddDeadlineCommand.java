package com.duke.command;

import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.Storage;

public class AddDeadlineCommand extends Command {

    private Task task;

    public AddDeadlineCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the command to add deadline task into the list.
     * @param tasks List of Task
     * @param storage Storage used by Duke
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.add(task);
        return "Got itm I've added this tasks:\n " + tasks.get(tasks.getCount()-1)
                + "\n" + "Now you have " + tasks.getCount() + " tasks in the list.";
    }
}
