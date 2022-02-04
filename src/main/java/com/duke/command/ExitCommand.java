package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.TaskList;

public class ExitCommand extends Command {

    /**
     * Execute the command to exit the Duke program.
     * @param tasks List of Task
     * @param storage Storage used by Duke
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        storage.save(tasks);
        return "Bye. Hope to see you again soon!";
    }
}
