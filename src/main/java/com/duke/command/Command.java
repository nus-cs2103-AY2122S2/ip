package com.duke.command;

import com.duke.util.Storage;
import com.duke.task.TaskList;
import com.duke.util.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}
