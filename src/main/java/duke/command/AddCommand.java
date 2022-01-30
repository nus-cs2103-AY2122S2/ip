package duke.command;

import duke.operations.TaskList;
import duke.task.Task;

/**
 * Represents a subclass of Command.
 */
public class AddCommand extends Command {
    /**
     * A constructor for <code>AddCommand</code>.
     *
     * @param task the task to be instantiated with.
     */
    public AddCommand(Task task) {
        super(task, null, null);
    }

    /**
     * Adds the task into the arraylist.
     *
     * @param taskList taskList the task to be added into the arraylist.
     * @return the task to be printed out by GUI.
     */
    @Override
    public String execute(TaskList taskList) {
        taskList.addToList(super.task);
        String output = "Task added!\n   " + task.toString();
        String totalNumOfTasks = taskList.totalTasks(taskList.getTaskArrayListSize());
        return output + "\n" + totalNumOfTasks;
    }
}
