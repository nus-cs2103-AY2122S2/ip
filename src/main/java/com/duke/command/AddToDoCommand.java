package com.duke.command;

import com.duke.task.Task;
import com.duke.task.TaskList;
import com.duke.util.Storage;
import com.duke.util.Ui;

public class AddToDoCommand extends Command {

    private final Task task;

    public AddToDoCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.showMsg("\t Got it, I've added this task:");
        ui.showMsg("\t  " + tasks.get(tasks.getCount()-1));
        ui.showMsg("\t Now you have " + tasks.getCount() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
