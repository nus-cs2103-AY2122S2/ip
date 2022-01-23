package duke.command;

import duke.task.Task;
import duke.operations.TaskList;

/**
 * An abstract class to handle the commands of the logic from Parser class.
 */
public abstract class Command {
    protected Task task;
    protected Integer num;

    /**
     * A constructor for <code>Command</code>.
     * Since it is an abstract class, Command cannot be instantiated.
     *
     * @param task the task to be stored.
     * @param num the number to be stored.
     */
    public Command(Task task, Integer num) {
        this.task = task;
        this.num = num;
    }

    /**
     * Executes tasks.
     *
     * @param tasks the task to be executed.
     */
    public abstract void execute(TaskList tasks);

    /**
     * Checks whether to terminate or not.
     *
     * @return if true terminate, else continue.
     */
    public abstract boolean isExit();
}
