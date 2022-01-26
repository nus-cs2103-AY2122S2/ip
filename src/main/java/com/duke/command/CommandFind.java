package com.duke.command;

import com.duke.modules.TaskList;

public class CommandFind extends Command {
    private String input;
    private TaskList taskList;

    public CommandFind(String input, TaskList taskList) {
        super();
        this.input = input;
        this.taskList = taskList;
    }

    @Override
    public CommandResult execute() {
        if (input.equals("")) {
            return new CommandResult("Please enter a keyword(s) to find");
        } else {
            return new CommandResult(taskList.displayCustomList(taskList.findMatch(input)));
        }
    }
}
