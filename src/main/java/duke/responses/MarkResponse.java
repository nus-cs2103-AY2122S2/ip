package duke.responses;

import duke.task.Task;

/***
 * Response to the MarkResponse Command
 */

public class MarkResponse implements Response {

    private Task currTask;

    /**
     * Constructs the response based on the Task
     * @param currTask
     */
    public MarkResponse(Task currTask) {
        this.currTask = currTask;
    }

    /**
     * Callback function that displays the intended results
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += Response.DIVIDER + "\n";
        uiResponse += "Nice! I've marked this task as done:\n";
        uiResponse += currTask.display() + "\n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
