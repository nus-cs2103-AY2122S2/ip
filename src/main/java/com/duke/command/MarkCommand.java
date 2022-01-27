package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.TaskList;
import com.duke.util.Ui;

public class MarkCommand extends Command {

    private final int pos;

    public MarkCommand(int pos) {
        this.pos = pos;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.get(pos-1).markAsDone();
        ui.showMsg("\t Nice! I've marked this task as done:\n" + "\t  " + tasks.get(pos-1));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
