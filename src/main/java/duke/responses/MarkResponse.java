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
    public void callback() {
        System.out.println(
                "____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(this.currTask.display());
        System.out.println(
                "____________________________________________________________");
    }
}
