package com.duke.command;

import com.duke.modules.TaskList;

public abstract class Command {
    protected TaskList taskList;

    public abstract CommandResult execute();
}

