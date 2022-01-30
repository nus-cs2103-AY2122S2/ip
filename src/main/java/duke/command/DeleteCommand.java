package duke.command;

import duke.operations.TaskList;
import duke.task.Task;

/**
 * Represents a subclass of Command.
 */
public class DeleteCommand extends Command {
    /**
     * A constructor for <code>DeleteCommand</code>.
     *
     * @param task the task to be instantiated with.
     */
    public DeleteCommand(Task task) {
        super(task, null, null);
    }

    /**
     * Deletes the task into the arraylist.
     *
     * @param taskList the task to be deleted from the arraylist.
     * @return the task to be printed out by GUI.
     */
    @Override
    public String execute(TaskList taskList) {
        taskList.deleteFromList(super.task);
        String firstMessage = "Hmm... kinda sus you deleted this task...";
        String secondMessage = task.toString();
        String totalNumOfTasks = taskList.totalTasks(taskList.getTaskArrayListSize());
        return firstMessage + "\n   " + secondMessage + "\n" + totalNumOfTasks;
    }
}
