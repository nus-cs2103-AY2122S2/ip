package duke.responses;

import duke.task.Task;

/***
 * Response to the UnMarkResponse Command
 */

public class UnMarkResponse implements Response {
    private Task currTask;

    /**
     * Constructs the response based on the Task
     * @param currTask the task to unmark
     */
    public UnMarkResponse(Task currTask) {
        this.currTask = currTask;
    }

    /**
     * Callback function that displays the intended results
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += Response.DIVIDER + "\n";
        uiResponse += "OK, I've marked this task as not done yet:\n";
        uiResponse += currTask.display() + "\n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
