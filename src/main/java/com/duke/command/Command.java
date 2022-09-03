package com.duke.command;

import com.duke.modules.TaskList;

/**
 * Represents a chatbot command.
 * This is an abstract class and cannot be instantiated.
 */
public abstract class Command {
    protected TaskList taskList;

    public abstract CommandResult execute();
}

