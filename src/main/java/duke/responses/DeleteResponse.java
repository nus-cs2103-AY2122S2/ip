package duke.responses;

import duke.task.Task;

/**
 * Type of response that is created when a task is deleted.
 */
public class DeleteResponse implements Response {
    private Task del;
    private int index;
    private int size;

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
    public String callback() {
        String uiResponse = "";
        uiResponse += Response.DIVIDER + "\n";
        uiResponse += "Noted. I've removed this task:\n";
        uiResponse += del.display() + "\n";
        uiResponse += "Now you have " + size + " in the list\n";
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
