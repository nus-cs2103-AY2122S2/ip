package duke.command.add;

import duke.command.Command;
import duke.command.CommandFeedback;
import duke.command.CommandType;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to add a task into the task list. An
 * <code>AddCommand</code> object records the specific task input
 * by the user. When executing the object, the task being stored
 * will be added into the task list.
 */
public abstract class AddCommand extends Command {
    private final Task newTask;

    /**
     * Creates an instance of a AddCommand object.
     *
     * @param newTask the new task being added to the task list.
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Returns a command feedback after the execution of the
     * AddCommand which add a task into the task list.
     *
     * @param taskList a list of task.
     * @return a command feedback of CommandType.ADD.
     */
    @Override
    public CommandFeedback execute(TaskList taskList) {

        taskList.add(newTask);

        return new CommandFeedback(CommandType.ADD, taskList, newTask);
    }
}
