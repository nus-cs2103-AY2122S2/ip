package com.duke.command;

import com.duke.modules.Storage;
import com.duke.modules.TaskList;

import java.io.IOException;

public class CommandClear extends Command {
    private String input;
    private TaskList taskList;

    public CommandClear(String input, TaskList taskList) {
        super();
        this.input = input;
        this.taskList = taskList;
    }

    @Override
    public CommandResult execute() {
        try {
            if (input.length() != 0) {
                return new CommandResult("A clearls command should not be succeeded by any words. "
                        + "Did you meant to do something else?");
            } else {
                taskList.clearList();
                return new CommandResult("List successfully cleared");
            }
        } catch (IOException e) {
            return new CommandResult("Unable to save list." +
                    "Please check if you have permission to write to files in the following directory: "
                    + Storage.getInstance().getDirectoryPath());
        }
    }
}
