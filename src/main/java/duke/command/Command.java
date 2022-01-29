package duke.command;

import duke.operations.TaskList;
import duke.task.Task;

/**
 * An abstract class to handle the commands of the logic from Parser class.
 */
public abstract class Command {
    protected Task task;
    protected Integer num;
    protected String keyword;

    /**
     * A constructor for <code>Command</code>.
     * Since it is an abstract class, Command cannot be instantiated.
     *
     * @param task the task to be stored.
     * @param num the number to be stored.
     * @param keyword the keyword to be stored.
     */
    public Command(Task task, Integer num, String keyword) {
        this.task = task;
        this.num = num;
        this.keyword = keyword;
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
