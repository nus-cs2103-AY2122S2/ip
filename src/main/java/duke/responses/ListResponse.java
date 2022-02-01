package duke.responses;

import duke.data.TaskList;

/**
 * Response that is created in the event of List command
 */
public class ListResponse implements Response {

    private TaskList iterate;
    private int count = 1;

    /**
     * Constructor for ListResponse.
     * @param iterate TaskList
     */
    public ListResponse(TaskList iterate) {
        this.iterate = iterate;
    }

    /**
     * Callback function that displays the intended results
     */
    @Override
    public String callback() {
        String uiResponse = "";
        uiResponse += Response.DIVIDER + "\n";
        uiResponse += "Here are the tasks in your list:\n";
        for (int i = 0; i < iterate.taskLength(); i++) {
            uiResponse += i + " " + iterate.getTask(i).display() + "\n";
        }
        uiResponse += Response.DIVIDER + "\n";
        return uiResponse;
    }
}
