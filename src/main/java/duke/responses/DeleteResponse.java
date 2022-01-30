package duke.responses;

import duke.task.Task;

/**
 * Type of response that is created when a task is deleted.
 */
public class DeleteResponse implements Response {

    Task del;
    int index;
    int size;

    /**
     * @param del This is the task deleted
     * @param size This is the size of the remaining interger
     */
    public DeleteResponse(Task del, int size) {
        this.del = del;
        this.size = size;
    }

    /**
     * Callback function that displays the intended results
     */
    @Override
    public void callback() {
        System.out.println(
                "____________________________________________________________"
        );
        System.out.println("Noted. I've removed this task:");
        System.out.println(del.display());
        System.out.println("Now you have " + size + " in the list");
        System.out.println(
                "____________________________________________________________"
        );
    }
}
