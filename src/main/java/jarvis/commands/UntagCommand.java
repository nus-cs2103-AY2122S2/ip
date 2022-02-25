package jarvis.commands;

import jarvis.exceptions.TagNotFoundException;
import jarvis.tasks.TaskList;

public class UntagCommand extends Command {
    private final int taskIdx;
    private final String tagName;

    /**
     * Constructor
     *
     * @param taskIdx index of the task
     */
    public UntagCommand(int taskIdx, String tagName) {
        this.taskIdx = taskIdx;
        this.tagName = tagName;
    }

    /**
     * Function to execute the command and get the result.
     *
     * @param taskList taskList object
     */
    @Override
    public String getResult(TaskList taskList) throws TagNotFoundException {
        return taskList.untagTask(taskIdx, tagName);
    }
}
