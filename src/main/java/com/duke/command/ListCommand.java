package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Execute the command to show the list of task
     * @param tasks List of Task
     * @param storage Storage used by Duke
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String response = "Here are the tasks in your list:\n";
        for (int i=0; i<tasks.getCount(); i++) {
            response = response + (i+1) + "." + tasks.get(i) + "\n";
        }
        return response;
    }
}
