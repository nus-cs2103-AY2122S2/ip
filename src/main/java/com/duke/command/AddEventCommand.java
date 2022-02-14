package com.duke.command;

import com.duke.task.Event;
import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.Storage;

public class AddEventCommand extends Command {

    private String input;

    public AddEventCommand(String input) {
        this.input = input;
    }

    /**
     * Execute the command to add event task into the list.
     * @param tasks List of Task
     * @param storage Storage used by Duke
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String des = input.split(" /", 2)[0].split(" ", 2)[1];
        String date = input.split("/", 2)[1].split(" ", 2)[1];
        tasks.add(new Event(des, date));
        return "Got itm I've added this tasks:\n " + tasks.get(tasks.getCount()-1)
                + "\n Now you have " + tasks.getCount() + " tasks in the list.";
    }
}
