package jarvis.commands;

import jarvis.tasks.Task;
import jarvis.tasks.TaskList;

public class TaskTagsCommand extends Command {
    private final int taskIdx;

    /**
     * Constructor
     *
     * @param taskIdx index of the task
     */
    public TaskTagsCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    /**
     * Function to execute the command and get the result.
     *
     * @param taskList TaskList object
     */
    @Override
    public String getResult(TaskList taskList) {
        Task task = taskList.getTask(taskIdx);
        return task.getTags().toString();
    }
}
