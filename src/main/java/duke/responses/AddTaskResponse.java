package duke.responses;

import duke.data.TaskList;
import duke.task.Task;

/**
 * Response when added a Task
 */

public class AddTaskResponse implements Response {
    private Task currTask;
    private TaskList tasklist;

    /**
     * Constructor for the AddTaskResponse.
     * @param currTask Task that is created.
     * @param tasklist The List of Task.
     */
    public AddTaskResponse(Task currTask, TaskList tasklist) {
        this.currTask = currTask;
        this.tasklist = tasklist;
    }

    /**
     * Callback function that displays the intended results.
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += Response.DIVIDER + "\n";
        uiResponse += currTask.display() + "\n";
        uiResponse += "Now you have " + tasklist.getTaskList().size() + " tasks in this list. \n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
