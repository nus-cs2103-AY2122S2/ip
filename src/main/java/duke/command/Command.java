package duke.command;

import duke.functionality.TaskList;
import duke.task.Task;

/**
 * Represents the commands inputted by a user. The Command class cannot be instantiated as it is an abstract class.
 */
public abstract class Command {
    protected Task task;
    /**
     * Constructor of Command class.
     * @param task task object created from user input.
     */
    public Command(Task task) {
        this.task = task;
    }

    /**
     * Returns a string, after the execution of the respective methods in TaskList class.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     * @return a String representing the respective messages after the execution of the methods in TaskList class.
     */
    public abstract String execute(TaskList tasks);

    /**
     * Returns a boolean depending on the input command of the user.
     * @return returns true if Command is an ExitCommand, eg: "bye". Else returns false.
     */
    public abstract boolean isExit();
}
