package com.duke.command;

import com.duke.modules.TaskList;

/**
 * Represents a chatbot command for displaying Tasks in the TaskList
 */
public class CommandList extends Command {
    private String input;
    private TaskList taskList;

    /**
     * Constructor for this class.
     *
     * @param input    The string of arguments entered by the user, excluding the command word.
     * @param taskList The tasklist meant for the Tasks to be added to.
     */
    public CommandList(String input, TaskList taskList) {
        super();
        this.input = input;
        this.taskList = taskList;
    }

    /**
     * Returns a string of the result of executing the intended function of this class.
     * This string is wrapped in a CommandResult object.
     *
     * @return A CommandResult object containing the result message.
     */
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
