package ann.commands;

import ann.data.tasks.TaskType;
import ann.data.tasks.Task;
import ann.data.tasks.Event;
import ann.data.tasks.Deadline;

/**
 * Represents a user command to add a task to the user's task list.
 *
 * @author Hong Anh
 * @version 0.1
 */
public class AddCommand extends Command{
    /** Represents the type of task being added. */
    private TaskType taskType;
    /** Represents the task being added. */
    private Task task;

    /**
     * Creates an AddCommand with the specified TaskType and task components.
     *
     * @param tt a TaskType representing the task's type.
     * @param taskComponents a String array containing information about the task.
     */
    public AddCommand(TaskType tt, String[] taskComponents) {
        taskType = tt;
        setTask(taskComponents);
    }

    /**
     * Creates a new Task with the specified task components and
     * sets the 'task' field to this newly created Task.
     *
     * @param taskComponents a String array containing information about the task.
     */
    private void setTask(String[] taskComponents) {
        switch (taskType) {
            case TODO:
                task = new Task(taskComponents[0]);
                break;
            case DEADLINE:
                task = new Deadline(taskComponents[0], taskComponents[1]);
                break;
            case EVENT:
                task = new Event(taskComponents[0], taskComponents[1]);
        }
    }

    /**
     * Adds the newly created Task to the instance's TaskList and sets the
     * 'message' field to reflect this change.
     */
    @Override
    public void executeCommand() {
        super.taskList.addTask(task);
        super.setMessage("Alright! I've added this task:\n" + task.toString()
                + "\n" + taskList.getNumTasksString());
    }
}