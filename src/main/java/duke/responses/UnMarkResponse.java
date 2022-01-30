package duke.responses;


import duke.task.Task;

/***
 * Response to the UnMarkResponse Command
 */

public class UnMarkResponse implements Response {
    Task currTask;

    /**
     * Constructs the response based on the Task
     * @param currTask 
     */
    public UnMarkResponse(Task currTask) {
        this.currTask = currTask;
    }

    /**
     * Callback function that displays the intended results
     */
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.currTask.display());
        System.out.println(
                "____________________________________________________________");
    }
}
