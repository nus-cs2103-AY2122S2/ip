package duke.command;

import duke.Response;
import duke.Task;
import duke.TaskList;

/**
 * Encapsulates the delete command.
 */
public class CommandDelete extends Command {
    private final TaskList taskList;
    private final int taskNo;

    /**
     * Constructor for CommandDelete
     * @param taskList The list of task to be modified.
     * @param taskNo The number of the task in the list.
     */
    public CommandDelete(TaskList taskList, int taskNo) {
        this.taskList = taskList;
        this.taskNo = taskNo;
    }

    /**
     * Deletes The task from the taskList
     * @return Response message of the user regarding successful deletion.
     */
    @Override
    public String execute() {
        Task deletee = taskList.deleteTask(taskNo);
        return Response.RESPONSE_DELETED + "\n" + deletee.toString() + "\n" + Response.taskNo(taskList.size());
    }
}
