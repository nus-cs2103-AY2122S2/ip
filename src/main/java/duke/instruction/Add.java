package main.java.duke.instruction;

import main.java.duke.task.Task;
import main.java.duke.task.TaskManager;
import main.java.duke.ui.Ui;

/**
 * Represents the 'add' instruction to main.Duke.
 */
public final class Add extends Instruction {

    private final Task task;

    /**
     * Constructor for an add instruction.
     *
     * @param task The task to be added.
     * @param tasks The task manager to be used.
     */
    protected Add(Task task, TaskManager tasks) throws IllegalArgumentException {

        super("add", tasks);
        this.task = task;
    }

    /**
     * Adds the task into the task manager, and returns the message when the action is done.
     *
     * @param ui The UI to be used.
     */
    @Override
    public void act(Ui ui) {
        ui.printMessage(tasks.addTask(this.task));
    }
}
