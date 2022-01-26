package com.duke.command;

import com.duke.modules.TaskList;

public class CommandList extends Command {
    private String input;
    private TaskList taskList;

    public CommandList(String input, TaskList taskList) {
        super();
        this.input = input;
        this.taskList = taskList;
    }

    @Override
    public CommandResult execute() {
        if (!input.equals("")) {
            return new CommandResult("The 'list' keyword is reserved for displaying lists. "
                    + "Please enter it as a single word"
                    + "\nEg: list");
        } else {
            return new CommandResult(taskList.displayList());
        }
    }
}
