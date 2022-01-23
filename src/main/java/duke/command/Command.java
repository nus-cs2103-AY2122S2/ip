package duke.command;

import duke.task.Task;
import duke.functionality.TaskList;

/**
 * Represents the commands inputted by a user. The Command class cannot be instantiated as it is an abstract class.
 */
public abstract class Command{
    protected Task task;
    protected Integer index;

    /**
     * Constructor of Command class.
     * @param t task object created from user input.
     * @param number an indicator to the index of the taskList in TaskList class.
     */
    public Command(Task t, Integer number) {
        this.task = t;
        this.index = number;
    }

    /**
     * Returns nothing, but used to execute the respective methods in TaskList class.
     * @param tasks an object of TaskList, used to access public methods in TaskList class.
     */
    public abstract void execute(TaskList tasks);

    /**
     * Returns a boolean depending on the input command of the user.
     * @return returns true if Command is an ExitCommand, eg: "bye". Else returns false.
     */
    public abstract boolean isExit();
}
