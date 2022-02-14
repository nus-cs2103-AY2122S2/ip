package com.duke.command;

import com.duke.exception.DukeException;
import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.task.Todo;
import com.duke.util.Storage;

public class AddToDoCommand extends Command {

    private String input;

    public AddToDoCommand(String input) {
        this.input = input;
    }

    /**
     * Execute the command to add the todo task into the list.
     * @param tasks List of Task
     * @param storage Storage used by Duke
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String[] ls = input.split(" ", 2);
        if (ls.length <= 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String des = ls[1];
        tasks.add(new Todo(des));
        return "Got itm I've added this tasks:\n " + tasks.get(tasks.getCount()-1)
                + "\n" + "Now you have " + tasks.getCount() + " tasks in the list.";
    }
}
