package duke.command;

import duke.Response;
import duke.Task;
import duke.TaskList;

/**
 * Encapsulates the delete command.
 */
public class CommandDelete extends Command {
    private String response;
    private final TaskList taskList;
    private final int taskNo;

    /**
     * Constructor for CommandDelete
     * @param taskList The list of task to be modified.
     * @param taskNo The number of the task in the list.
     */
    public CommandDelete(TaskList taskList, int taskNo) {
        assert taskList != null;
        this.taskList = taskList;
        this.taskNo = taskNo;
    }

    /**
     * Deletes The task from the taskList
     */
    @Override
    public void execute() {
        Task deletee = taskList.deleteTask(taskNo);
        response = Response.RESPONSE_DELETED + "\n" + deletee.toString() + "\n" + Response.taskNo(taskList.size());
    }

    @Override
    public String getResponse() {
        return response;
    }
}
