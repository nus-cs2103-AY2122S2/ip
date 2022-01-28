package main.java.ari.command;

import main.java.ari.tasks.Task;

/**
 * Represents a command to add Tasks to TaskList
 */
public abstract class AddCommand extends Command {
    protected static final String ADD_MESSAGE = "Understood, I have added this task to the list:\n"
            + "    %s\n"
            + "You have %d task(s) currently";

    protected Task task;

    @Override
    public String execute() {
        taskList.addTask(task);
        return String.format(ADD_MESSAGE, task, taskList.getSize());
    }

}
