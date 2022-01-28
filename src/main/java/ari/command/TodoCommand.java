package main.java.ari.command;

import main.java.ari.tasks.ToDoTask;

/**
 * Adds a ToDoTask to TaskList
 */
public class TodoCommand extends AddCommand {
    public static final String COMMAND_WORD = "todo";

    public TodoCommand(String arg) {
        this.task = new ToDoTask(arg);
    }
}
