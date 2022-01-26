package ari.command;

import ari.tasks.ToDoTask;

public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String arg) {
        this.task = new ToDoTask(arg);
    }
}
