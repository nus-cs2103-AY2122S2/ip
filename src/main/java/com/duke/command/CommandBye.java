package com.duke.command;

import com.duke.modules.TaskList;

public class CommandBye extends Command {
    private String input;

    public CommandBye(String input, TaskList taskList) {
        super();
        this.input = input;
    }

    @Override
    public CommandResult execute() {
        if (input.length() != 0) {
            return new CommandResult("A bye command should not be succeeded by any words. "
                    + "Did you meant to do something else?");
        } else {
            return CommandResult.shutdownResult();
        }
    }
}
